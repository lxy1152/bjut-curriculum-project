package server.action;

import commons.Request;
import commons.Response;
import commons.ServerAction;
import commons.User;
import commons.util.XStreamUtil;

public class PropAction extends ServerAction {

	@Override
	public void execute(Request request, Response response) {
		// TODO Auto-generated method stub
		String usePropUserID = (String) request.getParameter("usePropUserID");
		String beUsedPropUserID = (String) request
				.getParameter("beUsedPropUserID");
		int propType = (int) request.getParameter("propType");
		response.setData("propType", propType);
		// response.setActionClass(request.getClientActionClass());
		User u = server.getUsers().get(beUsedPropUserID);
		u.getPrintStream().println(XStreamUtil.toXML(response));
		server.appendServerPanel(usePropUserID + "对" + beUsedPropUserID
				+ "使用了道具" + propType);
	}

}
