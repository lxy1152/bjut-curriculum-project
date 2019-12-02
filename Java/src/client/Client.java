package client;

import client.ui.LoginFrame;

public class Client {
	
	private LoginFrame loginFrame;
	
	public Client(){
		loginFrame=new LoginFrame();
	}
	
	public static void main(String[] args){
		new Client();
	}
	
}
