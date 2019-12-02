package client.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import client.ui.ClientPanel;
import client.util.ImageUtil;

public class ConfigFrame extends JFrame {
	private JTabbedPane tabbedPane;

	private JPanel keyConfigPanel;

	private JPanel imageConfigPanel;

	private JPanel soundConfigPanel;

	private JPanel okPanel;

	private JLabel useProp1;
	private JLabel useProp2;
	private JLabel useProp3;
	private JLabel useProp4;
	private JLabel useProp5;
	private JLabel useProp6;
	private JLabel turnLeft;
	private JLabel turnRight;
	private JLabel moveLeft;
	private JLabel moveRight;
	private JLabel moveDown;
	private JLabel getDown;
	private JLabel changeProp;

	private KeyInput useProp1KeyConfig;
	private KeyInput useProp2KeyConfig;
	private KeyInput useProp3KeyConfig;
	private KeyInput useProp4KeyConfig;
	private KeyInput useProp5KeyConfig;
	private KeyInput useProp6KeyConfig;
	private KeyInput turnLeftKeyConfig;
	private KeyInput turnRightKeyConfig;
	private KeyInput moveLeftKeyConfig;
	private KeyInput moveRightKeyConfig;
	private KeyInput moveDownKeyConfig;
	private KeyInput getDownKeyConfig;
	private KeyInput changePropKeyConfig;

	private JCheckBox soundBox;

	private JButton okButton;

	private Game game;

	public ConfigFrame(Game game) {
		super();
		this.game = game;
		setSize(300, 400);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - getWidth()) / 2,
				(screenSize.height - getHeight()) / 2);
		this.setResizable(false);
		setIconImage(ImageUtil.getIcon().getImage());
		this.setVisible(true);
		// this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		initTabberPane();
		add(tabbedPane,BorderLayout.CENTER);
		initOKPanel();
		add(okPanel,BorderLayout.SOUTH);
	}

	public void initOKPanel() {
		okPanel = new JPanel();
		okButton = new JButton("设置完成");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateConfig();
			}
		});
		okPanel.add(okButton);
	}

	public void initTabberPane() {
		tabbedPane = new JTabbedPane();

		initKeyConfigPanel();
		imageConfigPanel = new JPanel();
		soundConfigPanel = new JPanel();
		initSoundConfigPanel();
		tabbedPane.addTab("游戏设置", keyConfigPanel);
		tabbedPane.addTab("图像设置", imageConfigPanel);
		tabbedPane.addTab("音频设置", soundConfigPanel);
	}

	private void initKeyConfigPanel() {

		useProp1 = new JLabel("对1号玩家使用道具");
		useProp2 = new JLabel("对2号玩家使用道具");
		useProp3 = new JLabel("对3号玩家使用道具");
		useProp4 = new JLabel("对4号玩家使用道具");
		useProp5 = new JLabel("对5号玩家使用道具");
		useProp6 = new JLabel("对6号玩家使用道具");
		turnLeft = new JLabel("变形（逆时针）");
		turnRight = new JLabel("变形（顺时针）");
		moveLeft = new JLabel("方块左移 ");
		moveRight = new JLabel("方块右移");
		moveDown = new JLabel("方块加速下落");
		getDown = new JLabel("方块直接下落");
		changeProp = new JLabel("切换道具");

		useProp1KeyConfig = new KeyInput(KeyEvent.VK_1);
		useProp2KeyConfig = new KeyInput(KeyEvent.VK_2);
		useProp3KeyConfig = new KeyInput(KeyEvent.VK_3);
		useProp4KeyConfig = new KeyInput(KeyEvent.VK_4);
		useProp5KeyConfig = new KeyInput(KeyEvent.VK_5);
		useProp6KeyConfig = new KeyInput(KeyEvent.VK_6);
		turnLeftKeyConfig = new KeyInput(KeyEvent.VK_0);
		turnRightKeyConfig = new KeyInput(KeyEvent.VK_UP);
		moveLeftKeyConfig = new KeyInput(KeyEvent.VK_LEFT);
		moveRightKeyConfig = new KeyInput(KeyEvent.VK_RIGHT);
		moveDownKeyConfig = new KeyInput(KeyEvent.VK_DOWN);
		getDownKeyConfig = new KeyInput(KeyEvent.VK_SPACE);
		changePropKeyConfig = new KeyInput(KeyEvent.VK_S);

		keyConfigPanel = new JPanel();
		keyConfigPanel.setLayout(new GridLayout(14, 2));
		keyConfigPanel.add(useProp1);
		keyConfigPanel.add(useProp1KeyConfig);
		keyConfigPanel.add(useProp2);
		keyConfigPanel.add(useProp2KeyConfig);
		keyConfigPanel.add(useProp3);
		keyConfigPanel.add(useProp3KeyConfig);
		keyConfigPanel.add(useProp4);
		keyConfigPanel.add(useProp4KeyConfig);
		keyConfigPanel.add(useProp5);
		keyConfigPanel.add(useProp5KeyConfig);
		keyConfigPanel.add(useProp6);
		keyConfigPanel.add(useProp6KeyConfig);
		keyConfigPanel.add(turnLeft);
		keyConfigPanel.add(turnLeftKeyConfig);
		keyConfigPanel.add(turnRight);
		keyConfigPanel.add(turnRightKeyConfig);
		keyConfigPanel.add(moveLeft);
		keyConfigPanel.add(moveLeftKeyConfig);
		keyConfigPanel.add(moveRight);
		keyConfigPanel.add(moveRightKeyConfig);
		keyConfigPanel.add(moveDown);
		keyConfigPanel.add(moveDownKeyConfig);
		keyConfigPanel.add(getDown);
		keyConfigPanel.add(getDownKeyConfig);
		keyConfigPanel.add(changeProp);
		keyConfigPanel.add(changePropKeyConfig);
	}

	private void initSoundConfigPanel() {
		soundBox = new JCheckBox("开启音效", game.isSound());
		soundConfigPanel.add(soundBox);
	}

	private void updateConfig() {
		game.getTetrisPanel().setMoveDownKey(moveDownKeyConfig.getKeyCode());
		game.getTetrisPanel().setMoveLeftKey(moveLeftKeyConfig.getKeyCode());
		game.getTetrisPanel().setMoveRightKey(moveRightKeyConfig.getKeyCode());
		game.getTetrisPanel().setTurnLeftKey(turnLeftKeyConfig.getKeyCode());
		game.getTetrisPanel().setTurnRightKey(turnRightKeyConfig.getKeyCode());
		game.setSound(soundBox.isSelected());
		this.setVisible(false);
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
