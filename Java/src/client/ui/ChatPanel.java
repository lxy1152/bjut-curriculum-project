package client.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.game.Game;

public class ChatPanel extends JPanel {
	private Game game;
	private JScrollPane scrollPane;
	private JTextArea info;

	public ChatPanel(Game game) {
		setGame(game);
		game.setChatPanel(this);
		setLayout(new BorderLayout());
		info = new JTextArea();
		// 禁止编辑
		info.setEditable(false);
		// 自动换行
		info.setLineWrap(true);

		scrollPane = new JScrollPane(info);
		add(scrollPane, BorderLayout.CENTER);
	}

	public void updateInfo(String str) {
		info.append(str + "\n");
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
