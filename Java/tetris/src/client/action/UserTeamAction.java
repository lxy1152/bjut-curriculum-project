package client.action;

import commons.ClientAction;
import commons.Response;
import commons.User;

public class UserTeamAction extends ClientAction {

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		String changeTeamUserID = (String) response.getData("changeTeamUserID");
		String team = (String) response.getData("team");
		for (int i = 0; i < user.getGame().getUsers().length; i++) {
			User u = user.getGame().getUsers()[i];
			if (u.getId().equals(changeTeamUserID)) {
				u.setTeam(team);
				break;
			}
		}
		user.getGame().getClientPanel().repaint();
	}
}
