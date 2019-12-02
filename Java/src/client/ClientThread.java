package client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import commons.ClientAction;
import commons.Response;
import commons.User;
import commons.util.XStreamUtil;

public class ClientThread extends Thread {
	private User user;

	private String line;

	private Map<String, ClientAction> actions = new HashMap<String, ClientAction>();

	public ClientThread(User user) {
		this.user = user;
	}

	public void run() {
		try {
			InputStream is = user.getSocket().getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				Response response = getResponse(line);
				// 得到客户端的处理类
				ClientAction action = getClientAction(response.getActionClass());
				// 执行客户端处理类
				action.execute(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 得到服务器响应中的客户端处理类
	private ClientAction getClientAction(String className) {
		try {
			if (actions.get(className) == null) {
				ClientAction action = (ClientAction) Class.forName(className)
						.newInstance();
				action.setUser(user);
				actions.put(className, action);
			}
			return actions.get(className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// 将服务器响应的xml转换成Response对象
	private Response getResponse(String xml) {
		return (Response) XStreamUtil.fromXML(xml);
	}

}
