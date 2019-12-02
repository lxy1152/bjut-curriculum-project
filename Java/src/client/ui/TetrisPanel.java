package client.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;
import javax.swing.JPanel;

import client.game.Block;
import client.game.Game;
import client.game.Piece;
import client.util.AudioManager;

public class TetrisPanel extends JPanel implements KeyListener {

	private Game game;

	private int moveDownKey = KeyEvent.VK_DOWN, moveLeftKey = KeyEvent.VK_LEFT,
			moveRightKey = KeyEvent.VK_RIGHT, turnLeftKey = KeyEvent.VK_0,
			turnRightKey = KeyEvent.VK_UP, getDownKey = KeyEvent.VK_SPACE;

	public TetrisPanel(Game game) {
		super();
		addKeyListener(this);
		setOpaque(false);
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setMoveDownKey(int moveDownKey) {
		this.moveDownKey = moveDownKey;
	}

	public void setMoveLeftKey(int moveLeftKey) {
		this.moveLeftKey = moveLeftKey;
	}

	public void setMoveRightKey(int moveRightKey) {
		this.moveRightKey = moveRightKey;
	}

	public void setTurnLeftKey(int turnLeftKey) {
		this.turnLeftKey = turnLeftKey;
	}

	public void setTurnRightKey(int turnRightKey) {
		this.turnRightKey = turnRightKey;
	}

	public void setGetDownKey(int getDownKey) {
		this.getDownKey = getDownKey;
	}

	public void drawHitBottomBlock(Graphics g, JComponent component) {
		Piece piece;
		for (int i = 0; i < Game.HEIGHT; i++) {
			for (int j = 0; j < Game.WIDTH; j++) {
				int type = game.getTetrisSpace()[i][j];
				if (type != 0) {
					int x = j * Game.SQUARE_SIZE;
					int y = i * Game.SQUARE_SIZE;
					piece = new Piece(type);
					piece.draw(g, x, y, component);
				}
			}
		}
	}

	public void drawResult(Graphics g) {
		String rankStr = Integer.toString(game.getUser().getRank());
		String numOfBlockStr = Integer
				.toString(game.getHitBottomBlock().size());
		String numOfCutLineStr = Integer.toString(game.getNumOfCutLine());
		String numOfUseProp = Integer.toString(game.getNumOfUseProp());
		String numOfAttackLine = Integer.toString(game.getNumOfAttackLine());
		String winTimes = Integer.toString(game.getUser().getWinTimes());
		String loseTimes = Integer.toString(game.getUser().getLoseTimes());
		String totalTimesString = Integer.toString(game.getUser()
				.getTotalTimes());
		g.setColor(Color.WHITE);
		g.drawString("游戏结束，你为第" + rankStr + "名!", 40, 180);
		g.drawString("总共方块：" + numOfBlockStr, 40, 200);
		g.drawString("总共消层：" + numOfCutLineStr, 40, 220);
		g.drawString("攻击消层：" + numOfAttackLine, 40, 240);
		g.drawString("使用道具：" + numOfUseProp, 40, 260);
		g.drawString("胜利盘数：" + winTimes, 40, 280);
		g.drawString("失败盘数：" + loseTimes, 40, 300);
		g.drawString("总共盘数：" + totalTimesString, 40, 320);
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (game != null)
			// 画已经落下的块
			drawHitBottomBlock(g, this);

		if (game.isStart()) {
			// 画当前的块
			game.getNowBlock().drawBlock(g, this);
		} else if (!game.getUser().getReady()
				&& game.getUser().getTotalTimes() >= 1) {
			drawResult(g);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		Block nowBlock = game.getNowBlock();
		if (keyCode == moveDownKey) {
			nowBlock.moveDown();
			if (game.isSound())
				AudioManager.playMoveSound();
		} else if (keyCode == moveLeftKey) {
			nowBlock.moveLeft();
			if (game.isSound())
				AudioManager.playMoveSound();
		} else if (keyCode == moveRightKey) {
			nowBlock.moveRight();
			if (game.isSound())
				AudioManager.playMoveSound();
		} else if (keyCode == turnLeftKey) {
			nowBlock.turnLeft();
			if (game.isSound())
				AudioManager.playTurnSound();
		} else if (keyCode == turnRightKey) {
			nowBlock.turnRight();
			if (game.isSound())
				AudioManager.playTurnSound();
		} else if (keyCode == getDownKey) {
			nowBlock.getDown();
			if (game.getTetrisThread() != null)
				game.getTetrisThread().interrupt();
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
