package client.action;

import commons.ClientAction;
import commons.Response;

/**
 * 接受消息
 */

public class ReceiveMessageAction extends ClientAction {

	public void execute(Response response) {
		String userName = (String) response.getData("userName");
		String message = (String) response.getData("message");

		user.getGame().getChatPanel().updateInfo(userName + "说：" + message);

	}

}
