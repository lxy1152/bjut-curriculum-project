package client.game;

import client.util.AudioManager;

public class TetrisThread extends Thread {
	private Game game;

	public TetrisThread(Game game) {
		this.game = game;
	}

	public void run() {
		Block nowBlock;
		while (game.isStart()) {
			nowBlock = game.getNowBlock();
			// 检查当前是否触底，没触底时自动向下移动
			if (!nowBlock.isHitBottom()) {
				try {
					System.out.println("线程正在休眠");
					sleep(game.getSpeed());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("有块直接落底！");
					if (game.isSound())
						AudioManager.playHitBottomSound();
				}
				nowBlock.moveDown();
			} else if (nowBlock.isHitTop()) {
				// 游戏结束
				System.out.println("游戏结束");
				game.over();
				if (game.isOnline()) {
					game.uploadTetrisSpace();
					game.sendGameOverAction();
				}
			} else {
				System.out.println("有块落底了！");
				if (game.isSound())
					AudioManager.playHitBottomSound();
				game.update();
				game.getPreviewPanel().repaint();
				game.getPropPanel().repaint();
			}
			game.getTetrisPanel().repaint();
		}
		System.out.println("游戏线程停掉了！");
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
