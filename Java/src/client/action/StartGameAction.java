package client.action;

import commons.ClientAction;
import commons.Response;

public class StartGameAction extends ClientAction{

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		user.getGame().start();
		user.getGame().unReadyAllUsers();
	}

}
