package client.action;

import commons.ClientAction;
import commons.Response;

public class GetRankAction extends ClientAction {

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		int rank = (int) response.getData("rank");
		user.setRank(rank);

		if (rank == 1) {
			user.win();
		} else {
			user.lose();
		}
		
		user.getGame().getTetrisPanel().repaint();

	}

}
