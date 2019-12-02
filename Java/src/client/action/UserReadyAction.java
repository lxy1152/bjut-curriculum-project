package client.action;

import commons.ClientAction;
import commons.Response;
import commons.User;

public class UserReadyAction extends ClientAction{

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		String readyUserID=(String)response.getData("readyUserID");
		User[] users=user.getGame().getUsers();
		for(int i=0;i<users.length;i++){
			if(users[i]!=null&&users[i].getId().equals(readyUserID)){
				users[i].setReady(true);
				break;
			}
		}
		user.getGame().getClientPanel().repaint();
	}

}
