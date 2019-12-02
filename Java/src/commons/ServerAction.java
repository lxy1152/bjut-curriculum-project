package commons;

import java.net.Socket;

import server.Server;

/**
 * 服务器处理请求的抽象类
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
