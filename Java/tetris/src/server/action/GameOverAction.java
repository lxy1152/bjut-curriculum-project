package server.action;

import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

public class GameOverAction extends ServerAction {

	@Override
	public void execute(Request request, Response response) {
		// TODO Auto-generated method stub
		String userID = (String) request.getParameter("userID");
		User user = server.getUsers().get(userID);
		user.setReady(false);

		// 判断游戏是否结束
		boolean gameIsOver = true;
		int count = 0;
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			if (u.getReady())
				count++;
		}

		int rank = count + 1;

		if (count > 1)
			gameIsOver = false;

		response.setData("gameIsOver", gameIsOver);
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			u.getPrintStream().println(XStreamUtil.toXML(response));
		}

		// 如果游戏结束则给给当前剩下的这个人发送其名次和游戏结束的信息
		if (gameIsOver) {
			for (String key : server.getUsers().keySet()) {
				User u = server.getUsers().get(key);
				if (u.getReady()) {
					u.setReady(false);
					response.setActionClass("client.action.GetRankAction");
					response.setData("rank", 1);
					u.getPrintStream().println(XStreamUtil.toXML(response));
		
				}
			}
		}

		// 给当前死掉的用户发送其名次。
		response.setActionClass("client.action.GetRankAction");
		response.setData("rank", rank);
		user.getPrintStream().println(XStreamUtil.toXML(response));

		if (gameIsOver) {
			server.appendServerPanel("游戏结束了！");
		}
	}

}
