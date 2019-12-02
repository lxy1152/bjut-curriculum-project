package commons;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务器响应对象
 */
public class Response {

	//服务器返回的各个数值
	private Map<String, Object> datas;
	
	//错误代码
	private String errorCode;
	
	//客户端处理类, 该值保存在Request的请求参数的Map中
	private String actionClass;
	
	public Response(String actionClass) {
		datas = new HashMap<String, Object>();
		this.actionClass = actionClass;
	}
	
	public void setData(String key, Object value) {
		datas.put(key, value);
	}
	
	public Object getData(String key) {
		return datas.get(key);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	
}
