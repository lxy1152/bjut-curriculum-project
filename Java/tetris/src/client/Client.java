package client;

import client.ui.LoginFrame;

/**
 * 客户端对象
 * 
 * @author Howen howen1994@163.com
 * @version 1.0 <br>
 *          Copyright (C), 2014, Howen <br>
 *          This program is protected by copyright laws.
 */

public class Client {
	
	private LoginFrame loginFrame;
	
	public Client(){
		loginFrame=new LoginFrame();
	}
	
	public static void main(String[] args){
		new Client();
	}
	
}
