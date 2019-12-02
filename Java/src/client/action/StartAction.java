package client.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import commons.ClientAction;
import commons.Response;
import commons.User;

public class StartAction extends ClientAction {

	public void execute(Response response) {
		Map<String, User> usersMap = (Map<String, User>) response
				.getData("users");
		List<User> users = getUsers(usersMap);
		if (users.size() != 0)
			for (User u : users) {
				user.getGame().addNewUser(u);
			}
	}

	/**
	 * 将Map转换成List
	 * 
	 * @param usersMap
	 * @return
	 */
	private List<User> getUsers(Map<String, User> usersMap) {
		List<User> result = new ArrayList<User>();
		for (String key : usersMap.keySet()) {
			User user = usersMap.get(key);
			result.add(user);
		}
		return result;
	}

}
