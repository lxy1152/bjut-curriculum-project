package commons;

/**
 * 客户端处理服务器响应的抽象类
 * 
 * @author Howen howen1994@163.com
 * @version 1.0 <br>
 *          Copyright (C), 2013-2014, Howen Zhang <br>
 *          This program is protected by copyright laws.
 */
public abstract class ClientAction {
	protected User user;

	public void setUser(User user) {
		this.user = user;
	}

	public abstract void execute(Response response);
}
