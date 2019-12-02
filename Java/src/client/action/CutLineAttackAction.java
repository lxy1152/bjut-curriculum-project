package client.action;

import client.util.AudioManager;
import commons.ClientAction;
import commons.Response;

public class CutLineAttackAction extends ClientAction{

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		int attackLine=(int) response.getData("attackLine");
		user.getGame().addDeadLine(attackLine);
		user.getGame().uploadTetrisSpace();
		if(user.getGame().isSound())
			AudioManager.playCutLineAttackSound();
	}

}
