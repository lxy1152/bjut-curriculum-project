package client.action;

import commons.ClientAction;
import commons.Response;

public class GameOverAction extends ClientAction {

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		boolean gameIsOver = (boolean) response.getData("gameIsOver");
		
		if(gameIsOver&&user.getGame().isStart()){
			user.getGame().uploadTetrisSpace();
		}
		
		if (gameIsOver) {
			user.getGame().unReadyAllUsers();
			user.getGame().over();
			user.getGame().getClientPanel().overGame();
			
		}

	}

}
