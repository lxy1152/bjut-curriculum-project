package client.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class KeyInput extends JTextField implements KeyListener{
	
	private int keyCode;
	
	public KeyInput(int keyCode) {
		super();
		setEditable(false);
		this.keyCode=keyCode;
		setText(KeyEvent.getKeyText(keyCode));
		setBorder(BorderFactory.createEmptyBorder());
		addKeyListener(this);
	}
	
	public int getKeyCode() {
		return keyCode;
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keyCode=e.getKeyCode();
		setText(KeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
