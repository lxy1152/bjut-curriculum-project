package client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import commons.User;
import client.game.ConfigFrame;
import client.game.Game;
import client.util.ImageUtil;
import client.util.MyButton;

public class ClientPanel extends JPanel {

	// 游戏区域388，137
	private JLabel backGround;

	private MyButton close;// 765,5
	private MyButton minimize;// 715,5

	private MyButton set;// 486,8
	private MyButton help;// 580,8
	private JLabel helpWindow;// 0,0
	private JFrame helpFrame;

	private MyButton send;//
	private JTextField messageField;

	private MyButton a;//
	private MyButton b;//
	private MyButton c;//
	private MyButton self;//

	private MyButton start;//
	private MyButton exit;//
	private MyButton exercise;//

	private Rectangle previewBlockRectangle;
	private Rectangle tetrisSpaceRectangle;
	private Rectangle proprRectangle;
	private Rectangle chatRectangle;
	private Rectangle messageRectangle;
	private Rectangle m2Rectangle;
	private Rectangle m3Rectangle;
	private Rectangle m4Rectangle;
	private Rectangle m5Rectangle;
	private Rectangle m6Rectangle;

	private Rectangle u1;
	private Rectangle u2;
	private Rectangle u3;
	private Rectangle u4;
	private Rectangle u5;
	private Rectangle u6;

	private Rectangle[] uRects;

	private HashMap<String, Color> colors;

	// 15,45

	private Game game;

	public ClientPanel(Game game) {
		super();
		previewBlockRectangle = new Rectangle(291, 137, 90, 150);
		tetrisSpaceRectangle = new Rectangle(390, 139, 192, 336);
		proprRectangle = new Rectangle(390, 496, 192, 16);
		chatRectangle = new Rectangle(612, 122, 172, 191);
		messageRectangle = new Rectangle(611, 317, 130, 18);
		m2Rectangle = new Rectangle(17, 117, 96, 168);
		m3Rectangle = new Rectangle(140, 117, 96, 168);
		m4Rectangle = new Rectangle(17, 380, 96, 168);
		m5Rectangle = new Rectangle(140, 380, 96, 168);
		m6Rectangle = new Rectangle(263, 380, 96, 168);

		u1 = new Rectangle(389, 48, 194, 57);
		u2 = new Rectangle(17, 45, 97, 45);
		u3 = new Rectangle(140, 45, 97, 45);
		u4 = new Rectangle(17, 308, 97, 45);
		u5 = new Rectangle(140, 308, 97, 45);
		u6 = new Rectangle(263, 308, 97, 45);
		uRects = new Rectangle[6];
		uRects[0] = u1;
		uRects[1] = u2;
		uRects[2] = u3;
		uRects[3] = u4;
		uRects[4] = u5;
		uRects[5] = u6;

		colors = new HashMap<String, Color>();
		Color green = new Color(127, 255, 0);
		Color yellow = new Color(255, 215, 0);
		Color blue = new Color(64, 224, 205);
		Color red = new Color(255, 99, 71);

		colors.put("empty", Color.GRAY);
		colors.put("self", green);
		colors.put("a", yellow);
		colors.put("b", blue);
		colors.put("c", red);

		setIgnoreRepaint(true);

		this.game = game;
		initMonitorPanel();
		initPropPanel();
		initPreviewPanel();
		initChatPanel();
		initTetrisPanel();
		initClientPanel();
	}

	public void initMonitorPanel() {
		MonitorPanel m2 = new MonitorPanel(game);
		m2.setBounds(m2Rectangle);
		add(m2);
		m2.setGame(game);
		game.getMonitorPanels().add(m2);

		MonitorPanel m3 = new MonitorPanel(game);
		m3.setBounds(m3Rectangle);
		add(m3);
		m3.setGame(game);
		game.getMonitorPanels().add(m3);

		MonitorPanel m4 = new MonitorPanel(game);
		m4.setBounds(m4Rectangle);
		add(m4);
		m4.setGame(game);
		game.getMonitorPanels().add(m4);

		MonitorPanel m5 = new MonitorPanel(game);
		m5.setBounds(m5Rectangle);
		add(m5);
		m5.setGame(game);
		game.getMonitorPanels().add(m5);

		MonitorPanel m6 = new MonitorPanel(game);
		m6.setBounds(m6Rectangle);
		add(m6);
		m6.setGame(game);
		game.getMonitorPanels().add(m6);
	}

	public void initTetrisPanel() {
		TetrisPanel tetrisPanel = new TetrisPanel(game);
		tetrisPanel.setBounds(tetrisSpaceRectangle);
		add(tetrisPanel);
		tetrisPanel.setFocusable(true);
		game.setTetrisPanel(tetrisPanel);
	}

	public void initPreviewPanel() {
		PreviewPanel previewPanel = new PreviewPanel(game);
		previewPanel.setBounds(previewBlockRectangle);
		add(previewPanel);
		game.setPreViewPanel(previewPanel);
	}

	public void initPropPanel() {
		PropPanel propPanel = new PropPanel(game);
		propPanel.setBounds(proprRectangle);
		add(propPanel);
		game.setPropPanel(propPanel);
	}

	public void initClientPanel() {
		setLayout(null);

		// 初始化各种按钮
		initButton();

		// 初始化配置界面
		ConfigFrame configFrame = new ConfigFrame(game);
		configFrame.setVisible(false);
		game.setConfigFrame(configFrame);

		initBackGround();
	}

	public void initBackGround() {
		// 背景(放在最后)
		backGround = new JLabel(ImageUtil.getBackGround());
		backGround.setBounds(0, 0, 800, 600);
		add(backGround);
	}

	public void initButton() {

		// 最小化按钮
		minimize = new MyButton(game);
		minimize.setIcon(ImageUtil.getMinimize());
		minimize.setBounds(715, 5, ImageUtil.getMinimize().getIconWidth(),
				ImageUtil.getMinimize().getIconHeight());
		// 让最小化按钮起作用
		minimize.addMouseListener(new MouseAdapter() {
			// 实现最小化和关闭
			@Override
			public void mouseClicked(MouseEvent m) {
				JOptionPane.getFrameForComponent(ClientPanel.this)
						.setExtendedState(JFrame.ICONIFIED);
			}
		});
		add(minimize);

		// 关闭按钮
		close = new MyButton(game);
		close.setIcon(ImageUtil.getClose());
		close.setBounds(765, 5, ImageUtil.getClose().getIconWidth(), ImageUtil
				.getClose().getIconHeight());
		close.addMouseListener(new MouseAdapter() {
			// 实现关闭
			@Override
			public void mouseClicked(MouseEvent m) {
				// 还要关掉socket
				if (game.isOnline())
					game.quit();
				System.exit(0);
			}
		});
		add(close);

		// 设置按钮
		set = new MyButton(game);
		set.setIcon(ImageUtil.getSet());
		set.setBounds(486, 8, ImageUtil.getSet().getIconWidth(), ImageUtil
				.getSet().getIconHeight());
		set.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				if (game.getConfigFrame().isVisible())
					game.getConfigFrame().setVisible(false);
				else {
					game.getConfigFrame().setVisible(true);
				}
			}
		});
		add(set);

		// 帮助界面
		helpWindow = new JLabel(ImageUtil.getHelpWindow());
		helpFrame = new JFrame();
		helpFrame.setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		helpFrame.setLocation((screenSize.width - ImageUtil.getHelpWindow()
				.getIconWidth()) / 2, (screenSize.height - ImageUtil
				.getHelpWindow().getIconHeight()) / 2);
		helpFrame.setSize(ImageUtil.getHelpWindow().getIconWidth(), ImageUtil
				.getHelpWindow().getIconHeight());
		helpFrame.setResizable(false);
		helpFrame.setVisible(false);
		helpFrame.getContentPane().add(helpWindow);
		helpFrame.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				helpFrame.setVisible(false);
			}
		});

		// 帮助按钮
		help = new MyButton(game);
		help.setIcon(ImageUtil.getHelp());
		help.setBounds(580, 8, ImageUtil.getHelp().getIconWidth(), ImageUtil
				.getHelp().getIconHeight());
		help.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				if (helpFrame.isShowing())
					helpFrame.setVisible(false);
				else
					helpFrame.setVisible(true);
			}
		});
		add(help);

		// 发送按钮
		send = new MyButton(game);
		send.setIcon(ImageUtil.getSend());
		send.setBounds(742, 316, ImageUtil.getSend().getIconWidth(), ImageUtil
				.getSend().getIconHeight());
		send.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				// 发送信息
				send();
			}
		});
		add(send);

		// A按钮
		a = new MyButton(game);
		a.setIcon(ImageUtil.getA());
		a.setBounds(640, 363, ImageUtil.getA().getIconWidth(), ImageUtil.getA()
				.getIconHeight());
		a.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				// 发送信息
				if (game.isOnline() && !game.isStart()) {
					game.changeTeam("a");
				}
			}
		});
		add(a);

		// B按钮
		b = new MyButton(game);
		b.setIcon(ImageUtil.getB());
		b.setBounds(690, 363, ImageUtil.getB().getIconWidth(), ImageUtil.getB()
				.getIconHeight());
		b.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				// 发送信息
				if (game.isOnline() && !game.isStart()) {
					game.changeTeam("b");
				}
			}
		});
		add(b);

		// C按钮
		c = new MyButton(game);
		c.setIcon(ImageUtil.getC());
		c.setBounds(738, 363, ImageUtil.getC().getIconWidth(), ImageUtil.getC()
				.getIconHeight());
		c.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				// 发送信息
				if (game.isOnline() && !game.isStart()) {
					game.changeTeam("c");
				}
			}
		});
		add(c);

		// self按钮
		self = new MyButton(game);
		self.setIcon(ImageUtil.getSelf());
		self.setBounds(663, 414, ImageUtil.getSelf().getIconWidth(), ImageUtil
				.getSelf().getIconHeight());
		self.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				// 发送信息
				if (game.isOnline() && !game.isStart()) {
					game.changeTeam("self");
				}
			}
		});
		add(self);

		// self按钮
		start = new MyButton(game);
		start.setIcon(ImageUtil.getStart());
		start.setBounds(704, 471, ImageUtil.getStart().getIconWidth(),
				ImageUtil.getStart().getIconHeight());
		start.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				start.setIcon(ImageUtil.getStart0());
				start.setEnabled(false);

				game.initTetrisSpace();
				if (!game.isStart() && !game.isOnline()) {
					game.start();
				} else if (!game.isStart() && game.isOnline()) {
					game.sendUserReadyAction();
					game.sendStartGameAction();
				}

			}
		});
		add(start);

		// exit按钮
		exit = new MyButton(game);
		exit.setIcon(ImageUtil.getExit());
		exit.setBounds(704, 524, ImageUtil.getExit().getIconWidth(), ImageUtil
				.getExit().getIconHeight());
		exit.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				// 发送信息
				if (game.isOnline())
					game.quit();
				System.exit(0);
			}
		});
		add(exit);

		// exercise按钮
		exercise = new MyButton(game);
		exercise.setIcon(ImageUtil.getExercise0());
		exercise.setBounds(616, 524, ImageUtil.getExercise0().getIconWidth(),
				ImageUtil.getExercise0().getIconHeight());
		exercise.addMouseListener(new MouseAdapter() {
			// 实现
			@Override
			public void mouseClicked(MouseEvent m) {
				// 发送信息
			}
		});
		add(exercise);
	}

	public void initChatPanel() {
		ChatPanel chatPanel = new ChatPanel(game);
		chatPanel.setBounds(chatRectangle);
		add(chatPanel);

		messageField = new JTextField(20);
		messageField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					send();
				}

			}
		});
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.add(messageField, BorderLayout.CENTER);
		messagePanel.setBounds(messageRectangle);
		add(messagePanel);

	}

	public void startGame() {
		if (game.isOnline())
			initMonitorPanel();

		a.setIcon(ImageUtil.getA0());
		a.setEnabled(false);
		b.setIcon(ImageUtil.getB0());
		b.setEnabled(false);
		c.setIcon(ImageUtil.getC0());
		c.setEnabled(false);
		self.setIcon(ImageUtil.getSelf0());
		self.setEnabled(false);
	}

	public void overGame() {
		a.setIcon(ImageUtil.getA());
		a.setEnabled(true);
		b.setIcon(ImageUtil.getB());
		b.setEnabled(true);
		c.setIcon(ImageUtil.getC());
		c.setEnabled(true);
		self.setIcon(ImageUtil.getSelf());
		self.setEnabled(true);
		start.setIcon(ImageUtil.getStart());
		start.setEnabled(true);
	}

	public void send() {
		if (!messageField.equals("") && game.isOnline()) {
			game.sendMessage(messageField.getText());
			messageField.setText("");
		} else
			return;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public JTextField getMessageField() {
		return messageField;
	}

	public Rectangle[] getURects() {
		return uRects;
	}

	public void setURects(Rectangle[] uRects) {
		this.uRects = uRects;
	}

	public void drawUser(Graphics g, Rectangle uRect, User user, Color color) {
		fillRect(g, uRect, color);
		g.setColor(Color.BLACK);
		g.drawString("昵称:" + user.getName(), uRect.x + 15, uRect.y + 30);
	}

	public void fillRect(Graphics g, Rectangle rectangle, Color color) {
		g.setColor(color);
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public void paint(Graphics g) {
		super.paint(g);

		Color color = colors.get(game.getUser().getTeam());
		drawUser(g, u1, game.getUser(), color);
		g.drawString("NO.1", u1.x + 15, u1.y + 15);
		if (!game.isStart() && game.getUser().getReady())
			g.drawString("Ready!", u1.x + 55, u1.y + 15);

		fillRect(g, u2, colors.get("empty"));
		fillRect(g, u3, colors.get("empty"));
		fillRect(g, u4, colors.get("empty"));
		fillRect(g, u5, colors.get("empty"));
		fillRect(g, u6, colors.get("empty"));

		if (game.getCanDrawUser()[0]) {
			color = colors.get(game.getUsers()[0].getTeam());
			drawUser(g, u2, game.getUsers()[0], color);
			g.drawString("NO.2", u2.x + 15, u2.y + 15);
			if (!game.isStart() && game.getUsers()[0].getReady())
				g.drawString("Ready!", u2.x + 55, u2.y + 15);
		}
		if (game.getCanDrawUser()[1]) {
			color = colors.get(game.getUsers()[1].getTeam());
			drawUser(g, u3, game.getUsers()[1], color);
			g.drawString("NO.3", u3.x + 15, u3.y + 15);
			if (!game.isStart() && game.getUsers()[1].getReady())
				g.drawString("Ready!", u3.x + 55, u3.y + 15);
		}
		if (game.getCanDrawUser()[2]) {
			color = colors.get(game.getUsers()[2].getTeam());
			drawUser(g, u4, game.getUsers()[2], color);
			g.drawString("NO.4", u4.x + 15, u4.y + 15);
			if (!game.isStart() && game.getUsers()[2].getReady())
				g.drawString("Ready!", u4.x + 55, u4.y + 15);
		}
		if (game.getCanDrawUser()[3]) {
			color = colors.get(game.getUsers()[3].getTeam());
			drawUser(g, u5, game.getUsers()[3], color);
			g.drawString("NO.5", u5.x + 15, u5.y + 15);
			if (!game.isStart() && game.getUsers()[3].getReady())
				g.drawString("Ready!", u5.x + 55, u5.y + 15);
		}
		if (game.getCanDrawUser()[4]) {
			color = colors.get(game.getUsers()[4].getTeam());
			drawUser(g, u6, game.getUsers()[4], color);
			g.drawString("NO.6", u6.x + 15, u6.y + 15);
			if (!game.isStart() && game.getUsers()[4].getReady())
				g.drawString("Ready!", u6.x + 55, u6.y + 15);
		}
	}

}
