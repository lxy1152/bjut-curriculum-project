package server.action;

import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

public class CutLineAttackAction extends ServerAction {

	@Override
	public void execute(Request request, Response response) {
		// TODO Auto-generated method stub
		String attackUserID = (String) request.getParameter("attackUserID");
		int attackLine = (int) request.getParameter("attackLine");
		String attackUserTeam = (String) request.getParameter("attackUserTeam");
		response.setData("attackLine", attackLine);

		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			if (!u.getId().equals(attackUserID)) {
				if (u.getTeam().equals("self")
						|| !u.getTeam().equals(attackUserTeam))
					u.getPrintStream().println(XStreamUtil.toXML(response));
			}
		}

	}

}
