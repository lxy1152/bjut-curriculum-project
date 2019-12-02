package client.action;

import client.game.Game;
import client.game.Prop;
import commons.ClientAction;
import commons.Response;

public class PropAction extends ClientAction{

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		Game game=user.getGame();
		//未写完！！！
		Prop prop=new Prop((int)response.getData("propType"));
		prop.setGame(game);
		prop.use();	
		game.uploadTetrisSpace();

	}

}
