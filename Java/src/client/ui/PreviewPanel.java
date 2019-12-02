package client.ui;

import java.awt.Graphics;
import javax.swing.JPanel;

import client.game.Game;
import client.util.ImageUtil;

public class PreviewPanel extends JPanel  {
	// 291 137
	private Game game;

	public PreviewPanel(Game game) {
		super();
		this.game = game;
		setOpaque(false);
	}

	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game){
		this.game=game;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (game.isStart()) {
			game.getNextBlock().drawBlock(g, 30, 35, this);
			game.getNextOfNextBlock().drawBlock(g, 30, 110,
					ImageUtil.getGraySuqare().getImage(), this);
		}
	}
}
