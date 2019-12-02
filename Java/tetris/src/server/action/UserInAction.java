package server.action;

import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

/**
 * 玩家进入游戏
 * 
 * @author Howen howen1994@163.com
 * @version 1.0 <br>
 *          Copyright (C), 2014, Howen <br>
 *          This program is protected by copyright laws.
 */

public class UserInAction extends ServerAction {

	public void execute(Request request, Response response) {

			
		// 得到新进入游戏的用户
		String userID = (String) request.getParameter("userID");
		String userName = (String) request.getParameter("userName");
		String userTeam = (String) request.getParameter("userTeam");

		User user = new User();
		user.setId(userID);
		user.setName(userName);
		user.setTeam(userTeam);
		// 设置用户的Socket
		user.setSocket(socket);

		response.setData("users", server.getUsers());
		user.getPrintStream().println(XStreamUtil.toXML(response));
		// 放到上下文中
		server.getUsers().put(user.getId(), user);
		// 告诉全部用户, 有新用户进入
		// 让客户端处理有新人进入聊天室的Action
		response.setActionClass("client.action.NewUserInAction");
		response.setData("newUserID", user.getId());
		response.setData("newUserName", user.getName());
		response.setData("newUserTeam", user.getTeam());
		// 向所有用户发送
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			if (u.getId() != user.getId()) {
				u.getPrintStream().println(XStreamUtil.toXML(response));
			}
		}
	}

}
