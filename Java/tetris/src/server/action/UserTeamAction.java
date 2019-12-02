package server.action;

import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

public class UserTeamAction extends ServerAction {

	@Override
	public void execute(Request request, Response response) {
		// TODO Auto-generated method stub
		String changeTeamUserID = (String) request
				.getParameter("changeTeamUserID");
		String team = (String) request.getParameter("team");
		server.getUsers().get(changeTeamUserID).setTeam(team);
		server.appendServerPanel(changeTeamUserID+ "加入了"
				+ team + "队！");

		response.setData("team", team);
		response.setData("changeTeamUserID", changeTeamUserID);
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			if (u.getId() != changeTeamUserID) {
				u.getPrintStream().println(XStreamUtil.toXML(response));
			}
		}
	}

}
