package server.action;

import java.util.ArrayList;
import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

public class StartGameAction extends ServerAction {

	@Override
	public void execute(Request request, Response response) {
		// 如果只有一个玩家，不开始游戏。
		if (server.getUsers().size() == 1)
			return;

		// 如果所有玩家属于一个队伍则不开始游戏。
		boolean allUsersAreInSameTeam = true;
		ArrayList<String> teams = new ArrayList<String>();
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			teams.add(u.getTeam());
		}
		
		String team1=teams.get(0);
		for (String team : teams) {
			if(team!=team1||team.equals("self"))
				allUsersAreInSameTeam=false;
		}
		if (allUsersAreInSameTeam)
			return;

		boolean canStartGame = true;
		for (String key : server.getUsers().keySet()) {
			User u = server.getUsers().get(key);
			if (!u.getReady()) {
				canStartGame = false;
			}
		}

		if (canStartGame) {
			for (String key : server.getUsers().keySet()) {
				User u = server.getUsers().get(key);
				u.getPrintStream().println(XStreamUtil.toXML(response));
			}
			server.appendServerPanel("游戏开始了！");
		}
	}

}
