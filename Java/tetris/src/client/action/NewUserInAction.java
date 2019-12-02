package client.action;

import commons.ClientAction;
import commons.Response;
import commons.User;

public class NewUserInAction extends ClientAction {

	public void execute(Response response) {
		String newUserID = (String) response.getData("newUserID");
		String newUserName = (String) response.getData("newUserName");
		String newUserTeam = (String) response.getData("newUserTeam");

		User newUser = new User();
		newUser.setId(newUserID);
		newUser.setName(newUserName);
		newUser.setTeam(newUserTeam);

		if (newUserID.equals(user.getId()))
			return;
		else {
			user.getGame().addNewUser(newUser);
		}

	}

}
