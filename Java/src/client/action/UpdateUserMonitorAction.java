package client.action;

import java.util.ArrayList;

import client.ui.MonitorPanel;
import commons.ClientAction;
import commons.Response;

public class UpdateUserMonitorAction extends ClientAction {

	@Override
	public void execute(Response response) {
		// TODO Auto-generated method stub
		String userID = (String) response.getData("updateUserID");
		int[][] tetrisSpace = (int[][]) response.getData("tetrisSpace");
		ArrayList<MonitorPanel> monitorPanels = user.getGame()
				.getMonitorPanels();
		addTetrisSpaceToMonitor(userID,monitorPanels,tetrisSpace);
		user.getGame().updateMonitorPanel();
	}

	public void addTetrisSpaceToMonitor(String userID,
			ArrayList<MonitorPanel> monitorPanels, int[][] tetrisSpace) {
		for (MonitorPanel monitorPanel : monitorPanels) {
			if (monitorPanel.getID()!=null&&monitorPanel.getID().equals(userID)) {
				monitorPanel.setTetrisSpace(tetrisSpace);
			}
		}
	}

}
