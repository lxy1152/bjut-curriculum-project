package client.util;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import client.game.Game;
import client.ui.ClientPanel;
import client.ui.TetrisPanel;

public class MyButton extends JButton implements Serializable{
	public MyButton(final Game game) {
		super();
		setBorder(BorderFactory.createEmptyBorder());
		setUI(new MyButtonUI());
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}

		});

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (game.isStart()) {
					game.getTetrisPanel().keyTyped(e);
					//clientPanel.getPropPanel().keyTyped(e);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (game.isStart()) {
					game.getTetrisPanel().keyReleased(e);
					//game.getPropPanel().keyReleased(e);
				}

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (game.isStart()) {
					game.getPropPanel().keyPressed(e);
					game.getTetrisPanel().keyPressed(e);
				}
			}
		});

	}
}
