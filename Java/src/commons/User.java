package commons;

import java.io.PrintStream;
import java.io.Serializable;
import java.net.Socket;

import javax.swing.JFrame;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import client.game.Game;
import client.ui.ClientPanel;
import client.ui.LoginFrame;
import client.ui.TetrisPanel;
import client.util.AudioManager;

/**
 * 玩家抽象类
 */
public class User implements Serializable {

	// 玩家的唯一标识
	private String id;

	// 人物图片
	private String showImage;

	// 玩家名称
	private String name;

	// 0男, 1女
	private int sex;

	// 玩家的队伍
	private String team;

	// 玩家的积分
	private int score;

	// 玩家的等级
	private String level;

	// 是否准备
	private boolean ready;

	// 是否在练习
	private boolean exercise;

	// 游戏信息
	@XStreamOmitField
	private Game game;

	// 玩家对应的Socket
	private Socket socket;

	private int rank;

	private boolean hasRank;

	private int winTimes;

	private int loseTimes;

	private int totalTimes;

	private LoginFrame loginFrame;

	public User() {
		rank = 1;
		winTimes = 0;
		loseTimes = 0;
		totalTimes = 0;
	}

	public User(String id, String showImage, String name, int sex) {
		super();
		this.id = id;
		this.showImage = showImage;
		this.name = name;
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShowImage() {
		return showImage;
	}

	public void setShowImage(String showImage) {
		this.showImage = showImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	synchronized public String getTeam() {
		return team;
	}

	synchronized public void setTeam(String team) {
		this.team = team;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	synchronized public boolean getReady() {
		return ready;
	}

	synchronized public void setReady(boolean ready) {
		this.ready = ready;
	}

	public boolean getExercise() {
		return exercise;
	}

	public void setExercise(boolean exercise) {
		this.exercise = exercise;
	}

	synchronized public Game getGame() {
		return game;
	}

	synchronized public void setGame(Game game) {
		this.game = game;
	}

	synchronized public Socket getSocket() {
		return socket;
	}

	synchronized public void setSocket(Socket socket) {
		this.socket = socket;
	}

	synchronized public int getRank() {
		return rank;
	}

	synchronized public void setRank(int rank) {
		this.rank = rank;
	}

	synchronized public int getLoseTimes() {
		return loseTimes;
	}

	synchronized public void setLoseTimes(int lostTimes) {
		this.loseTimes = lostTimes;
	}

	synchronized public int getWinTimes() {
		return winTimes;
	}

	synchronized public void setWinTimes(int winTimes) {
		this.winTimes = winTimes;
	}

	synchronized public int getTotalTimes() {
		return totalTimes;
	}

	synchronized public void setTotalTimes(int totalTimes) {
		this.totalTimes = totalTimes;
	}

	public void win() {
		winTimes++;
		totalTimes++;
		if (game.isSound())
			AudioManager.playWinSound();
	}

	public void lose() {
		loseTimes++;
		totalTimes++;
		if (game.isSound())
			AudioManager.playLoseSound();
	}

	public boolean hasRank() {
		return hasRank;
	}

	public void setHasRank(boolean hasRank) {
		this.hasRank = hasRank;
	}

	public LoginFrame getLoginFrame() {
		return loginFrame;
	}

	public void setLoginFrame(LoginFrame loginFrame) {
		this.loginFrame = loginFrame;
	}

	public PrintStream getPrintStream() {
		try {
			PrintStream ps = new PrintStream(this.socket.getOutputStream());
			return ps;
		} catch (Exception e) {
			return null;
		}
	}
}
