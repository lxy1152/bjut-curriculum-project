package client.ui;

import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JPanel;

import client.game.Game;
import client.game.Piece;

public class MonitorPanel extends JPanel {
	// 监视其他玩家的面板
	private Game game;

	public final static int SQUARE_SIZE = 8;
	private int tetrisSpace[][];
	private String id;

	public MonitorPanel(Game game) {
		super();
		this.game = game;
		setOpaque(false);
		tetrisSpace = new int[Game.HEIGHT][Game.WIDTH];
		initTetrisSpace();
	}

	public void initTetrisSpace() {
		for (int i = 0; i < Game.HEIGHT; i++) {
			for (int j = 0; j < Game.WIDTH; j++) {
				tetrisSpace[i][j] = 0;
			}
		}
	}

	public void drawHitBottomBlock(Graphics g, JComponent component) {
		Piece piece;
		for (int i = 0; i < Game.HEIGHT; i++) {
			for (int j = 0; j < Game.WIDTH; j++) {
				int type = tetrisSpace[i][j];
				if (type != 0) {
					int x = j * SQUARE_SIZE;
					int y = i * SQUARE_SIZE;
					piece = new Piece(type + 18);
					piece.draw(g, x, y, component);
				}
			}
		}
	}

	public void paint(Graphics g) {
		if (game.isStart()) {// &&game.isOnline()){
			drawHitBottomBlock(g, this);
		}
	}

	public void setTetrisSpace(int[][] tetrisSpace) {
		this.tetrisSpace = tetrisSpace;
	}

	public int[][] getTetrisSpace() {
		return tetrisSpace;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}
	
	public String getID(){
		return id;
	}
	
	public void setID(String id){
		this.id=id;
	}

}
