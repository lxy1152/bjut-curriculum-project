package client.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;
import java.util.Map;
import java.util.UUID;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import client.ClientException;
import client.ClientThread;
import client.game.Game;
import commons.Request;
import commons.User;
import commons.util.XStreamUtil;

public class LoginFrame extends JFrame {

	// 用户名
	private JLabel nameTextLabel = new JLabel("用户名：");
	private JTextField nameField = new JTextField(20);

	// 性别选择
	private JLabel sexTextLabel = new JLabel("性别：");
	private JComboBox sexSelect = new JComboBox();

	// 游戏秀
	private JLabel showTextLabel = new JLabel("游戏秀：");
	private JComboBox showSelect = new JComboBox<>();

	// 游戏模式选择
	private JLabel gameTextLabel = new JLabel("选择游戏模式：");
	private JComboBox gameSelect = new JComboBox();

	// 连接地址
	private JLabel connectionLabel = new JLabel("连接地址：");
	private JTextField connectionField = new JTextField("127.0.0.1");

	// 按钮
	private JButton confirmButton = new JButton("确定");
	private JButton cancelButton = new JButton("取消");

	// 游戏秀图片
	private Map<String, ImageIcon> shows;

	private User user;

	public LoginFrame() {
		sexSelect.addItem("男");
		sexSelect.addItem("女");

		gameSelect.addItem("联机对战");
		gameSelect.addItem("单机练习");
		// this.shows = ImageUtil.getShows();
		// 创建游戏秀选择下拉框
		// buildShowSelect();
		// 创建游戏模式选择下拉框
		// buildGameModeSelect();

		Box nameBox = Box.createHorizontalBox();
		nameBox.add(Box.createHorizontalStrut(30));
		nameBox.add(this.nameTextLabel);
		nameBox.add(Box.createHorizontalStrut(20));
		nameBox.add(this.nameField);
		nameBox.add(Box.createHorizontalStrut(30));

		Box sexBox = Box.createHorizontalBox();
		sexBox.add(Box.createHorizontalStrut(30));
		sexBox.add(this.sexTextLabel);
		sexBox.add(Box.createHorizontalStrut(33));
		sexBox.add(this.sexSelect);
		sexBox.add(Box.createHorizontalStrut(170));

		Box showBox = Box.createHorizontalBox();
		showBox.add(Box.createHorizontalStrut(30));
		showBox.add(this.showTextLabel);
		showBox.add(Box.createHorizontalStrut(33));
		showBox.add(this.showSelect);
		showBox.add(Box.createHorizontalStrut(170));

		Box gameBox = Box.createHorizontalBox();
		gameBox.add(Box.createHorizontalStrut(30));
		gameBox.add(this.gameTextLabel);
		gameBox.add(Box.createHorizontalStrut(7));
		gameBox.add(this.gameSelect);
		gameBox.add(Box.createHorizontalStrut(30));

		Box connectionBox = Box.createHorizontalBox();
		connectionBox.add(Box.createHorizontalStrut(30));
		connectionBox.add(this.connectionLabel);
		connectionBox.add(Box.createHorizontalStrut(7));
		connectionBox.add(this.connectionField);
		connectionBox.add(Box.createHorizontalStrut(30));

		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(this.confirmButton);
		buttonBox.add(Box.createHorizontalStrut(30));
		buttonBox.add(this.cancelButton);

		Box mainBox = Box.createVerticalBox();
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(nameBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(sexBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(showBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(connectionBox);
		mainBox.add(Box.createVerticalStrut(10));
		mainBox.add(gameBox);
		mainBox.add(Box.createVerticalStrut(20));
		mainBox.add(buttonBox);
		mainBox.add(Box.createVerticalStrut(20));

		add(mainBox);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getPreferredSize();
		setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);

		setTitle("游戏登录");
		pack();
		setVisible(true);
		initListeners();
		user = new User();
		user.setLoginFrame(this);
	}

	// 初始化按钮监听器
	private void initListeners() {
		this.confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		this.cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void login() {
		if (this.nameField.getText().equals("")) {
			JOptionPane.showConfirmDialog(null, "请输入名称", "提示",
					JOptionPane.OK_CANCEL_OPTION);
			return;
		}
		setUser();
		// 得到用户所选择的游戏
		String gameMode = (String) gameSelect.getSelectedItem();
		// 启动联机模式
		if (gameMode.equals("联机对战")) {
			// 设置User的各个属性
			user.setSocket(createSocket(connectionField.getText(), 12000));

			// 打开游戏界面
			user.setGame(new Game(true, user));

			// 启动线程
			ClientThread thread = new ClientThread(user);
			thread.start();

			// 进入游戏, 告诉服务器用户有用户进入
			Request request = new Request("server.action.UserInAction",
					"client.action.StartAction");
			request.setParameter("userID", user.getId());
			request.setParameter("userTeam", user.getTeam());
			request.setParameter("userName", user.getName());
			user.getPrintStream().println(XStreamUtil.toXML(request));

		} else {
			// 启动单机模式。
			user.setGame(new Game(false, user));
		}
		setVisible(false);
	}

	private Socket createSocket(String address, int port) {
		try {
			// 创建Socket
			return new Socket(address, port);
		} catch (Exception e) {
			throw new ClientException(ClientException.CANNOTCONNECT);
		}
	}

	private void setUser() {
		// 创建用户
		int sex = getSex();
		String name = nameField.getText();
		String id = UUID.randomUUID().toString();// 唯一标示符
		user.setId(id);
		user.setShowImage((String) showSelect.getSelectedItem());
		user.setName(name);
		user.setSex(sex);
		user.setTeam("self");
		user.setReady(false);
	}

	private int getSex() {
		String sex = (String) sexSelect.getSelectedItem();
		if (sex.equals("男"))
			return 0;
		else
			return 1;
	}

}
