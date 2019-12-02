package server;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import commons.Response;
import commons.User;
import commons.util.XStreamUtil;
import server.ui.ServerFrame;
import server.ui.ServerPanel;

/**
 * 服务器对象
 * 
 * @author Howen howen1994@163.com
 * @version 1.0 <br>
 *          Copyright (C), 2014, Howen <br>
 *          This program is protected by copyright laws.
 */

public class Server {

	private ServerFrame serverFrame;

	private ServerPanel serverPanel;

	private ServerSocket serverSocket;

	private Map<String, User> users = new HashMap<String, User>();

	private int userNum = 0;

	public Server() {
		serverFrame = new ServerFrame();
		serverPanel = new ServerPanel();
		serverFrame.add(serverPanel);
		serverFrame.initServerFrame();
	}

	private void run() {
		try {
			// 创建ServerSocket对象, 端口为12000
			serverSocket = new ServerSocket(12000);

			serverPanel.updateInfo("服务器建立成功！" + "\n" + "服务器IP："
					+ InetAddress.getLocalHost().getHostAddress() + "\n"
					+ "端口号：" + serverSocket.getLocalPort() + "\n");

			while (true) {
				// 监听Socket的连接
				Socket s = serverSocket.accept();

				if (userNum < 6) {
					serverPanel.updateInfo(s.getInetAddress().getHostAddress()
							+ "连接到服务器！" + "\n");
					serverPanel.updateUserNum("在线人数：" + (++userNum));
					// 启动服务器线程
					new ServerThread(this, s).start();
				} else {
					// 房间满了不让进！
					Response response = new Response(
							"client.action.RoomIsFullAction");
					User user = new User();
					user.setSocket(s);
					user.getPrintStream().println(XStreamUtil.toXML(response));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServerException("创建服务器异常: " + e.getMessage());
		}
	}

	synchronized public void userQuit(Socket s) {
		serverPanel.updateInfo(s.getInetAddress().getHostAddress() + "退出了！"
				+ "\n");
		serverPanel.updateUserNum("在线人数：" + (--userNum));
	}

	synchronized public void appendServerPanel(String string) {
		serverPanel.updateInfo(string + "\n");
	}

	synchronized public int getUserNum() {
		return userNum;
	}

	synchronized public Map<String, User> getUsers() {
		return users;
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.run();
	}
}
