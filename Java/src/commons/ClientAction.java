package commons;

/**
 * 客户端处理服务器响应的抽象类
 */
public abstract class ClientAction {
	protected User user;

	public void setUser(User user) {
		this.user = user;
	}

	public abstract void execute(Response response);
}
