package client.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import commons.Request;
import commons.util.XStreamUtil;
import client.game.Game;
import client.game.Prop;

public class PropPanel extends JPanel implements KeyListener {
	// 最多容纳12个道具
	private Game game;

	private int useProp1 = KeyEvent.VK_1, useProp2 = KeyEvent.VK_2,
			useProp3 = KeyEvent.VK_3, useProp4 = KeyEvent.VK_4,
			useProp5 = KeyEvent.VK_5, useProp6 = KeyEvent.VK_6,
			changeProp = KeyEvent.VK_S;

	public PropPanel(Game game) {
		super();
		this.game = game;
		setOpaque(false);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (game.isStart()) {
			ArrayList<Prop> propList = game.getPropList();
			for (Prop prop : propList) {
				g.drawImage(prop.getImage(), propList.indexOf(prop)
						* Game.SQUARE_SIZE, 0, this);
			}
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
		Prop prop = game.getNowProp();
		String ID = "";
		if (prop == null)
			return;
		else if (keyCode == useProp1) {
			prop.use();
			game.getPropList().remove(0);
			game.setNumOfUseProp(game.getNumOfUseProp() + 1);
			if (game.isOnline())
				game.uploadTetrisSpace();
		} else if (keyCode == useProp2 && game.isOnline()) {
			if (game.getUsers()[0] != null)
				ID = game.getUsers()[0].getId();
		} else if (keyCode == useProp3 && game.isOnline()) {
			if (game.getUsers()[1] != null)
				ID = game.getUsers()[1].getId();
		} else if (keyCode == useProp4 && game.isOnline()) {
			if (game.getUsers()[2] != null)
				ID = game.getUsers()[2].getId();
		} else if (keyCode == useProp5 && game.isOnline()) {
			if (game.getUsers()[3] != null)
				ID = game.getUsers()[3].getId();
		} else if (keyCode == useProp6 && game.isOnline()) {
			if (game.getUsers()[4] != null)
				ID = game.getUsers()[4].getId();
		} else if (keyCode == changeProp) {
			game.changeProp();
		}

		if (game.isOnline() && ID != "") {
			Request request = new Request("server.action.PropAction",
					"client.action.PropAction");
			request.setParameter("usePropUserID", game.getUser().getId());
			request.setParameter("beUsedPropUserID", ID);
			request.setParameter("propType", prop.getType());
			game.getUser().getPrintStream().println(XStreamUtil.toXML(request));
			game.setNumOfUseProp(game.getNumOfUseProp() + 1);
			game.getPropList().remove(0);
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
