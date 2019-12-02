package client.action;

import commons.ClientAction;
import commons.Response;

public class UserQuitAction extends ClientAction{

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		String quitUserID=(String) response.getData("quitUserID");
		user.getGame().deleteUser(quitUserID);
		user.getGame().sendStartGameAction();
	}

}
