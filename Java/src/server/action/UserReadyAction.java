package server.action;

import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

public class UserReadyAction extends ServerAction {

	@Override
	public void execute(Request request, Response response) {
		// TODO Auto-generated method stub
		// 得到准备好游戏的用户,并通知其他用户该用户准备了

		String userID = (String) request.getParameter("userID");
		User user = server.getUsers().get(userID);
		user.setReady(true);
		server.appendServerPanel(user.getSocket().getInetAddress()
				.getHostAddress()
				+ "准备好了！");

		response.setData("readyUserID", userID);
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			if (!key.equals(userID))
				u.getPrintStream().println(XStreamUtil.toXML(response));
		}
	}
}
