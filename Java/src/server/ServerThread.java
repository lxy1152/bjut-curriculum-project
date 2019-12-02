package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.util.XStreamUtil;

/**
 * 服务器引擎线程, 用于处理服务器接受请求与作出响应
 */

public class ServerThread extends Thread {

	private Socket socket;

	private BufferedReader br;

	private String line;

	private Server server;

	// 保存被创建的Action对象
	public Map<String, ServerAction> actions = new HashMap<String, ServerAction>();

	public ServerThread(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
	}

	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			while ((line = br.readLine()) != null) {
				// 得到请求对象
				Request request = getRequest(line);
				// 从request中得到客户端处理类, 并且构造Response对象
				Response response = new Response(request.getClientActionClass());
				// 将请求的参数都设置到Response中
				copyParameters(request, response);
				// 得到Server处理类
				ServerAction action = getAction(request.getServerActionClass());
				// 执行Action
				action.execute(request, response);

			}
		} catch (SocketException se) {
			System.out.println(socket.getRemoteSocketAddress() + " 退出了！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将Request中的参数map设置到Response的data中
	private void copyParameters(Request request, Response response) {
		Map<String, Object> parameters = request.getParameters();
		for (String key : parameters.keySet()) {
			response.setData(key, parameters.get(key));
		}
	}

	// 将字符串转换成一个Request对象
	private Request getRequest(String xml) {
		try {
			Request r = (Request) XStreamUtil.fromXML(xml);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 从Map中得到Action对象, 如果拿不到, 则创建
	private ServerAction getAction(String className) {
		try {
			if (actions.get(className) == null) {
				ServerAction action = (ServerAction) Class.forName(className)
						.newInstance();
				action.setServer(server);
				action.setSocket(socket);
				actions.put(className, action);
			}
			return actions.get(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
