package commons.util;

import client.game.Game;

import com.thoughtworks.xstream.XStream;

/**
 * XStream工具类, 用于xml与对象之间的转换
 */
public class XStreamUtil {

	private static XStream xstream = new XStream();
	 
	
	/**
	 * 将XML转换成对象
	 * @param xml
	 * @return
	 */
	public static Object fromXML(String xml) {
		return xstream.fromXML(xml);
	}
	
	/**
	 * 将对象转换成XML字段串
	 * @param obj
	 * @return
	 */
	public static String toXML(Object obj) {
		xstream.processAnnotations(Game.class); 
		String xml = xstream.toXML(obj);
		//去掉换行
		String a = xml.replaceAll("\n", "");
		String s = a.replaceAll("\r", "");
		return s;
	}

}

