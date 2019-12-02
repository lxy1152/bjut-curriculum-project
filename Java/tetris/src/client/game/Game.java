package client.game;

import java.util.ArrayList;
import java.util.Random;

import commons.Request;
import commons.User;
import commons.util.XStreamUtil;
import client.ui.ChatPanel;
import client.ui.ClientFrame;
import client.ui.ClientPanel;
import client.ui.MonitorPanel;
import client.ui.PreviewPanel;
import client.ui.PropPanel;
import client.ui.TetrisPanel;
import client.util.AudioManager;

//游戏类，包含游戏的所有面板和游戏信息。
public class Game {
	// 游戏数据
	private Block nextBlock;
	private Block nextOfNextBlock;
	private Block nowBlock;
	private int speed = 500;
	private ArrayList<Block> hitBottomBlock;
	private ArrayList<Prop> propList;
	private ArrayList<Integer> line;
	private int numOfCutLine;
	private int numOfCutLineOnce;
	private int numOfUseProp;
	private int numOfUser;
	private int numOfAttackLine;

	private User[] users;
	private boolean[] canDrawUser;

	// 游戏空间
	public final static int WIDTH = 12; // 游戏空间宽（单位：小正方格）(标准大小)
	public final static int HEIGHT = 21;
	int tetrisSpace[][] = new int[HEIGHT][WIDTH];
	public final static int SQUARE_SIZE = 16;

	// 游戏界面
	private ClientFrame clientFrame;
	private ClientPanel clientPanel;
	private TetrisPanel tetrisPanel;
	private PropPanel propPanel;
	private PreviewPanel previewPanel;
	private ConfigFrame configFrame;
	private ChatPanel chatPanel;

	// 实时显示其他游戏玩家情况的面板
	private ArrayList<MonitorPanel> monitorPanels;

	// 游戏线程
	private TetrisThread tetrisThread;

	private boolean isStart = false;
	private boolean isOver = false;
	private boolean online;
	private boolean whereDrawDeadLine = true;
	private boolean sound = true;

	// 当前游戏玩家
	private User user;

	public Game(boolean online, User user) {
		this.online = online;
		this.user = user;
		numOfUser = 1;
		users = new User[5];
		monitorPanels = new ArrayList<MonitorPanel>();
		clientFrame = new ClientFrame();
		clientPanel = new ClientPanel(this);
		clientFrame.initClientFrame();
		clientFrame.add(clientPanel);

		canDrawUser = new boolean[5];
		for (int i = 0; i < 5; i++) {
			canDrawUser[i] = false;
		}
	}

	public void initGame() {

		hitBottomBlock = new ArrayList<Block>();
		line = new ArrayList<Integer>();
		propList = new ArrayList<Prop>();

		nextBlock = Block.getRandomBlock();
		nextOfNextBlock = Block.getRandomBlock();
		nowBlock = Block.getRandomBlock();

		nowBlock.setGame(this);
		nextBlock.setGame(this);
		nextOfNextBlock.setGame(this);

		numOfCutLineOnce = 0;
		numOfCutLine = 0;
		numOfUseProp = 0;
		numOfAttackLine = 0;

		initTetrisSpace();
		updateNowBlock();
	}

	public void start() {
		isStart = true;
		isOver = false;
		clientPanel.startGame();
		// 初始化游戏区域并建立游戏线程
		initGame();
		int temp[][] = new int[HEIGHT][WIDTH];
		for (MonitorPanel monitorPanel : monitorPanels) {
			monitorPanel.setTetrisSpace(temp);
		}
		clientPanel.repaint();
		previewPanel.repaint();
		tetrisPanel.repaint();
		tetrisThread = new TetrisThread(this);
		tetrisThread.start();
		if (sound)
			AudioManager.playStartSound();
	}

	public void sendUserReadyAction() {
		user.setReady(true);
		Request request = new Request("server.action.UserReadyAction",
				"client.action.UserReadyAction");
		request.setParameter("userID", user.getId());
		request.setParameter("ready", user.getReady());
		user.getPrintStream().println(XStreamUtil.toXML(request));
		clientPanel.repaint();
	}

	public void sendStartGameAction() {
		Request request = new Request("server.action.StartGameAction",
				"client.action.StartGameAction");
		user.getPrintStream().println(XStreamUtil.toXML(request));
		clientPanel.repaint();
	}

	public void sendGameOverAction() {
		Request request = new Request("server.action.GameOverAction",
				"client.action.GameOverAction");
		request.setParameter("userID", user.getId());
		user.getPrintStream().println(XStreamUtil.toXML(request));
	}

	public void over() {
		user.setReady(false);
		isStart = false;
		isOver = true;
		changeTetrisSpaceToGray();
		clientPanel.repaint();
		previewPanel.repaint();
		tetrisPanel.repaint();
		if (!online) {
			clientPanel.overGame();
			user.setTotalTimes(user.getTotalTimes() + 1);
		}
	}

	public void quit() {
		Request request = new Request("server.action.UserQuitAction",
				"client.action.UserQuitAction");
		request.setParameter("quitUserID", user.getId());
		user.getPrintStream().println(XStreamUtil.toXML(request));
	}

	public void initTetrisSpace() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				tetrisSpace[i][j] = 0;
			}
		}
	}

	public void changeTetrisSpaceToGray() {
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				if (tetrisSpace[i][j] != 0) {
					tetrisSpace[i][j] = Piece.GRAY;
				}
			}
		}
	}

	synchronized public void updateBlock() {
		nowBlock = nextBlock;
		nextBlock = nextOfNextBlock;
		nextOfNextBlock = Block.getRandomBlock();
		nextOfNextBlock.setGame(this);
		updateNowBlock();
	}

	public void updateNowBlock() {
		nowBlock.setBlock_cx(WIDTH / 2 - 1);
		int type = nowBlock.getType();
		if (type == Piece.S || type == Piece.Z || type == Piece.T)
			nowBlock.setBlock_cy(1);
		else
			nowBlock.setBlock_cy(0);
	}

	synchronized public void updateMonitorPanel() {
		for (MonitorPanel monitorPanel : monitorPanels) {
			monitorPanel.repaint();
		}
	}

	synchronized public void update() {
		// 将其数据加入游戏空间
		addToTetrisSpace(nowBlock);
		hitBottomBlock.add(nowBlock);

		// 检查能否消行
		if (canCutLine()) {
			// 进行消行
			cutLine();
			// 如果在线 消行后检测是否达到攻击条件
			if (online && numOfCutLineOnce > 2) {
				numOfCutLineOnce--;
				Request request = new Request(
						"server.action.CutLineAttackAction",
						"client.action.CutLineAttackAction");
				request.setParameter("attackUserID", user.getId());
				request.setParameter("attackLine", numOfCutLineOnce);
				request.setParameter("attackUserTeam", user.getTeam());
				user.getPrintStream().println(XStreamUtil.toXML(request));
			}
			numOfAttackLine += numOfCutLineOnce;
			numOfCutLineOnce = 0;
		}

		// 得到新块
		updateBlock();

		// 是否获得新道具
		if (numOfCutLine != 0 && numOfCutLine % 2 == 0) {
			Random random = new Random();
			Prop prop = new Prop(random.nextInt(Piece.SLOW - Piece.ADD1 + 1)
					+ Piece.ADD1);
			addToTetrisSpace(prop);
		}
		if (online)
			uploadTetrisSpace();
	}

	public void uploadTetrisSpace() {
		Request request = new Request("server.action.UpdateUserMonitorAction",
				"client.action.UpdateUserMonitorAction");
		request.setParameter("userID", user.getId());
		request.setParameter("tetrisSpace", tetrisSpace);
		user.getPrintStream().println(XStreamUtil.toXML(request));
	}

	public void changeTeam(String team) {
		user.setTeam(team);
		Request request = new Request("server.action.UserTeamAction",
				"client.action.UserTeamAction");
		request.setParameter("changeTeamUserID", user.getId());
		request.setParameter("team", user.getTeam());
		user.getPrintStream().println(XStreamUtil.toXML(request));
		clientPanel.repaint();

	}

	synchronized public boolean canCutLine() {
		boolean canCutLine = true;
		for (int i = 0; i < HEIGHT; i++) {
			canCutLine = true;
			for (int j = 0; j < WIDTH; j++) {
				if (tetrisSpace[i][j] == 0) {
					canCutLine = false;
					break;
				}
			}
			if (canCutLine) {
				System.out.println("Can cut line!");
				line.add(i);
			}
		}

		if (!line.isEmpty())
			return true;
		else
			return false;
	}

	synchronized public void cutLine() {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (Integer i : line) {
			temp.add(i);

			// 取出道具
			for (int p = 0; p < WIDTH; p++) {
				if (tetrisSpace[i][p] >= Piece.ADD1
						&& tetrisSpace[i][p] <= Piece.SLOW) {
					Prop prop = new Prop(tetrisSpace[i][p]);
					prop.setGame(this);
					if (propList.size() < 12)// 道具满之后将不再添加
						propList.add(prop);
				}
			}

			// 坐标向下平移
			for (int k = i; k >= 0; k--) {
				for (int j = 0; j < WIDTH; j++) {
					if (k >= 1)
						tetrisSpace[k][j] = tetrisSpace[k - 1][j];
					else {
						tetrisSpace[k][j] = 0;
					}
				}
			}
		}
		for (Integer i : temp) {
			numOfCutLine++;
			numOfCutLineOnce++;
			line.remove(i);
		}
		AudioManager.playCutLineSound();
	}

	// 用道具导致加行的方法
	synchronized public void addDeadLine(int line) {
		// 坐标向上平移
		for (int i = 0; i < HEIGHT - line; i++) {
			for (int j = 0; j < WIDTH; j++) {
				tetrisSpace[i][j] = tetrisSpace[i + line][j];
			}
		}

		for (int j = HEIGHT - 1; j >= HEIGHT - line; j--) {
			for (int i = 0; i < WIDTH; i++) {
				tetrisSpace[j][i] = 0;
			}
			if (whereDrawDeadLine == true)
				for (int i = 0; i < WIDTH; i += 2) {
					tetrisSpace[j][i] = Piece.DEAD;
				}
			else
				for (int i = 1; i < WIDTH; i += 2) {
					tetrisSpace[j][i] = Piece.DEAD;
				}
			whereDrawDeadLine = !whereDrawDeadLine;
		}
	}

	// 用道具导致消行的方法
	synchronized public void subLine(int line) {
		// 坐标向下平移
		for (int i = HEIGHT - 1; i >= line; i--) {
			for (int j = 0; j < WIDTH; j++) {
				tetrisSpace[i][j] = tetrisSpace[i - line][j];
			}
		}

		for (int j = 0; j < line; j++) {
			for (int i = 0; i < WIDTH; i++) {
				tetrisSpace[j][i] = 0;
			}
		}
	}

	// 得到当前有块的行数
	public int getLine() {
		int count = 0;
		for (int i = HEIGHT - 1; i > 0; i--) {
			for (int j = 0; j < WIDTH; j++) {
				if (tetrisSpace[i][j] != 0) {
					count++;
					break;
				}
			}
		}
		return count;
	}

	// 将块的数据加到数据空间里
	public void addToTetrisSpace(Block block) {
		int x, y;
		for (int i = 0; i < 4; i++) {
			x = block.getBlock_cx() + block.getBlockBox()[i][0];
			y = block.getBlock_cy() + block.getBlockBox()[i][1];
			tetrisSpace[y][x] = block.getType();
		}
	}

	public int getNumOfPiece(int line) {
		int count = 0;
		for (int i = 0; i < WIDTH; i++) {
			if (tetrisSpace[line][i] != 0)
				count++;
		}
		return count;
	}

	public int getNumOfProp(int line) {
		int count = 0;
		for (int i = 0; i < WIDTH; i++) {
			if (tetrisSpace[line][i] >= Piece.ADD1
					&& tetrisSpace[line][i] <= Piece.SLOW)
				count++;
		}
		return count;
	}

	public boolean canAddToTetrisSpace(int y) {
		boolean can = true;
		if (getNumOfPiece(y) == getNumOfProp(y))
			can = false;
		return can;
	}

	// 将道具的数据随机加到数据空间里
	public void addToTetrisSpace(Prop prop) {
		int y = getRandomY();
		if (canAddToTetrisSpace(y)) {
			int x = getRandomX(y);
			tetrisSpace[y][x] = prop.getType();
		}
	}

	// 得到当前的道具
	public Prop getNowProp() {
		if (propList.isEmpty())
			return null;
		else
			return propList.get(0);
	}

	// 变换道具顺序
	public void changeProp() {
		if (propList.size() == 1)
			return;
		Prop temp = propList.get(0);
		ArrayList<Prop> tempList = new ArrayList<Prop>();
		for (int i = 1; i < propList.size(); i++) {
			tempList.add(propList.get(i));
		}
		tempList.add(temp);
		propList = tempList;
	}

	private int getRandomX(int y) {
		int x = 0;
		Random random = new Random();
		x = random.nextInt(WIDTH);
		while (tetrisSpace[y][x] == 0
				|| (tetrisSpace[y][x] >= Piece.ADD1 && tetrisSpace[y][x] <= Piece.SLOW)) {
			x = random.nextInt(WIDTH);
		}
		return x;
	}

	private int getRandomY() {
		int y = 0;
		Random random = new Random();
		y = HEIGHT - random.nextInt(getLine()) - 1;
		return y;
	}

	public void unReadyAllUsers() {
		for (int i = 0; i < 5; i++) {
			if (users[i] != null) {
				users[i].setReady(false);
			}
		}
	}

	// 增加新玩家
	synchronized public void addNewUser(User user) {
		numOfUser++;
		monitorPanels.get(numOfUser - 2).setID(user.getId());
		users[numOfUser - 2] = user;
		canDrawUser[numOfUser - 2] = true;
		clientPanel.repaint();
	}

	// 某玩家退出
	synchronized public void deleteUser(String userID) {
		int i;
		for (i = 0; i < users.length; i++) {
			if (users[i] != null && users[i].getId().equals(userID))
				break;
		}
		numOfUser--;
		monitorPanels.get(i).setID(null);
		users[i] = null;
		canDrawUser[i] = false;
		clientPanel.repaint();
	}

	public void sendMessage(String message) {
		chatPanel.updateInfo(user.getName()+" 说： "+message);
		Request request = new Request("server.action.MessageAction",
				"client.action.ReceiveMessageAction");
		request.setParameter("userID", user.getId());
		request.setParameter("message", message);
		user.getPrintStream().println(XStreamUtil.toXML(request));
	}

	public boolean[] getCanDrawUser() {
		return canDrawUser;
	}

	public User[] getUsers() {
		return users;
	}

	public void setUsers(User[] users) {
		this.users = users;
	}

	public boolean isOnline() {
		return online;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public ConfigFrame getConfigFrame() {
		return configFrame;
	}

	public void setConfigFrame(ConfigFrame configFrame) {
		this.configFrame = configFrame;
	}

	public ClientFrame getClientFrame() {
		return clientFrame;
	}

	public void setClientFrame(ClientFrame clientFrame) {
		this.clientFrame = clientFrame;
	}

	public TetrisThread getTetrisThread() {
		return tetrisThread;
	}

	public void setTetrisThread(TetrisThread tetrisThread) {
		this.tetrisThread = tetrisThread;
	}

	public TetrisPanel getTetrisPanel() {
		return tetrisPanel;
	}

	public void setTetrisPanel(TetrisPanel tetrisPanel) {
		this.tetrisPanel = tetrisPanel;
	}

	public PreviewPanel getPreviewPanel() {
		return previewPanel;
	}

	public void setPreViewPanel(PreviewPanel previewPanel) {
		this.previewPanel = previewPanel;
	}

	public PropPanel getPropPanel() {
		return propPanel;
	}

	public void setPropPanel(PropPanel propPanel) {
		this.propPanel = propPanel;
	}

	public void setClientPanel(ClientPanel clientPanel) {
		this.clientPanel = clientPanel;
	}

	public ClientPanel getClientPanel() {
		return clientPanel;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setNowBlock(Block nowBlock) {
		this.nowBlock = nowBlock;
	}

	public Block getNowBlock() {
		return nowBlock;
	}

	public void setNextBlock(Block nextBlock) {
		this.nextBlock = nextBlock;
	}

	public Block getNextBlock() {
		return nextBlock;
	}

	public void setNextOfNextBlock(Block nextOfNextBlock) {
		this.nextOfNextBlock = nextOfNextBlock;
	}

	public Block getNextOfNextBlock() {
		return nextOfNextBlock;
	}

	public void setHitBottomBlock(ArrayList<Block> hitBottomBlock) {
		this.hitBottomBlock = hitBottomBlock;
	}

	public ArrayList<Block> getHitBottomBlock() {
		return hitBottomBlock;
	}

	public ArrayList<Prop> getPropList() {
		return propList;
	}

	public void setPropList(ArrayList<Prop> propList) {
		this.propList = propList;
	}

	public void setTetrisSpace(int[][] tetrisSpace) {
		this.tetrisSpace = tetrisSpace;
	}

	public int[][] getTetrisSpace() {
		return tetrisSpace;
	}

	public int getNumOfUser() {
		return numOfUser;
	}

	public void setNumOfUser(int numOfUser) {
		this.numOfUser = numOfUser;
	}

	public int getNumOfCutLine() {
		return numOfCutLine;
	}

	public void setNumOfCutLine(int numOfCutLine) {
		this.numOfCutLine = numOfCutLine;
	}

	public int getNumOfAttackLine() {
		return numOfAttackLine;
	}

	public void setNumOfAttackLine(int numOfAttackLine) {
		this.numOfAttackLine = numOfAttackLine;
	}

	public int getNumOfUseProp() {
		return numOfUseProp;
	}

	public void setNumOfUseProp(int numOfUseProp) {
		this.numOfUseProp = numOfUseProp;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public ArrayList<MonitorPanel> getMonitorPanels() {
		return monitorPanels;
	}

	public void setMonitorPanels(ArrayList<MonitorPanel> monitorPanels) {
		this.monitorPanels = monitorPanels;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}
}
