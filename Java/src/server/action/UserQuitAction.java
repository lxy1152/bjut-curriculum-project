package server.action;

import java.io.IOException;
import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

public class UserQuitAction extends ServerAction{

	@Override
	public void execute(Request request, Response response) {
		// TODO Auto-generated method stub
		//得到退出游戏的用户
		String quitUserID=(String)request.getParameter("quitUserID");
		//设置用户的Socet
		//user.setSocket(socket);
		//告诉原来的用户, 启动游戏, 并发送所有的用户信息
		//response.setData("users", Server.users);
		//user.getPrintStream().println(XStreamUtil.toXML(response));
		//放到上下文中
		User quitUser=server.getUsers().get(quitUserID);
		server.userQuit(quitUser.getSocket());
		
		try {
			quitUser.getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		server.getUsers().remove(quitUserID);

		response.setActionClass(request.getClientActionClass());
		response.setData("quitUserID", quitUserID);
		//response.setData("deleteUser", user);
		//向所有用户发送
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			if (u.getId() != quitUserID) {
				u.getPrintStream().println(XStreamUtil.toXML(response));
			}
		}
	}

}
