package commons;

import java.net.Socket;

import server.Server;

/**
 * 服务器处理请求的抽象类
 * 
 * @author Howen howen1994@163.com
 * @version 1.0 <br>
 *          Copyright (C), 2014, Howen <br>
 *          This program is protected by copyright laws.
 */
public abstract class ServerAction {
	protected Server server;
	protected Socket socket;

	public void setServer(Server server) {
		this.server = server;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public abstract void execute(Request request, Response response);
}
