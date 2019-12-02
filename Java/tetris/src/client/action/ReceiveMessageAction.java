package client.action;

import commons.ClientAction;
import commons.Response;

/**
 * 接受消息
 * 
 * @author Howen howen1994@163.com
 * @version 1.0 <br>
 *          Copyright (C), 2014, Howen <br>
 *          This program is protected by copyright laws.
 */

public class ReceiveMessageAction extends ClientAction {

	public void execute(Response response) {
		String userName = (String) response.getData("userName");
		String message = (String) response.getData("message");

		user.getGame().getChatPanel().updateInfo(userName + "说：" + message);

	}

}
