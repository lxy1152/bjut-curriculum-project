package commons;


import java.util.HashMap;
import java.util.Map;

import commons.util.XStreamUtil;


/**
 * 请求对象, 表示客户端向服务器的一次请求
 * 
 * @author Howen howen1994@163.com
 * @version  1.0
 * <br>Copyright (C), 2014, Howen
 * <br>This program is protected by copyright laws.
 */

public class Request {

	//参数
	private Map<String, Object> parameters;
	
	//服务器处理类
	private String serverActionClass;
	
	private String clientActionClass;

	public Request(String serverActionClass, String clientActionClass) {
		this.serverActionClass = serverActionClass;
		parameters = new HashMap<String, Object>();
		this.clientActionClass = clientActionClass;
	}
	
	public Map<String, Object> getParameters() {
		return parameters;
	}
	
	public void setParameter(String key, Object value) {
		parameters.put(key, value);
	}
	
	public Object getParameter(String key) {
		return this.parameters.get(key);
	}

	public String getServerActionClass() {
		return serverActionClass;
	}

	public void setServerActionClass(String serverActionClass) {
		this.serverActionClass = serverActionClass;
	}

	public String getClientActionClass() {
		return clientActionClass;
	}

	public void setClientActionClass(String clientActionClass) {
		this.clientActionClass = clientActionClass;
	}

	public String toXML() {
		return XStreamUtil.toXML(this);
	}
}
