package server.action;

import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

public class UpdateUserMonitorAction extends ServerAction {

	@Override
	public void execute(Request request, Response response) {
		// TODO Auto-generated method stub
		String userID=(String)request.getParameter("userID");
		int[][] tetrisSpace = (int[][]) request.getParameter("tetrisSpace");
		
		response.setActionClass(request.getClientActionClass());
		response.setData("updateUserID", userID);
		response.setData("tetrisSpace", tetrisSpace);
		//向所有用户发送
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			if (u.getId() != userID) {
				u.getPrintStream().println(XStreamUtil.toXML(response));
			}
		}
		
	}

}
