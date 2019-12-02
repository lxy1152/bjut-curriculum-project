package client;

import javax.swing.JOptionPane;

/**
 * 客户端异常
 */
public class ClientException extends RuntimeException {
	public static final String CANNOTCONNECT = "cannot connect";
	public static final String ROOMISFULL = "room is full";

	public ClientException(String s) {
		super(s);
		if (s.equals(CANNOTCONNECT))
			JOptionPane.showConfirmDialog(null, "服务器连接超时！", "提示",
					JOptionPane.OK_CANCEL_OPTION);
		if (s.equals(ROOMISFULL))
			JOptionPane.showConfirmDialog(null, "所连接的房间已满！", "提示",
					JOptionPane.OK_CANCEL_OPTION);
	}
}
