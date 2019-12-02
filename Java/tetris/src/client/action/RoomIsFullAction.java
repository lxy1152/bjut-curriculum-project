package client.action;

import client.ClientException;
import commons.ClientAction;
import commons.Response;

/**
 * 房间满了执行的操作
 * 
 * @author Howen howen1994@163.com
 * @version 1.0 <br>
 *          Copyright (C), 2014, Howen <br>
 *          This program is protected by copyright laws.
 */

public class RoomIsFullAction extends ClientAction {

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		user.getGame().getClientFrame().setVisible(false);
		user.setGame(null);
		user.getLoginFrame().setVisible(true);
		throw new ClientException(ClientException.ROOMISFULL);
	}

}
