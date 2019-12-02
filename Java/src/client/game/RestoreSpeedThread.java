package client.game;

import java.io.Serializable;

public class RestoreSpeedThread extends Thread implements Serializable{

	private Game game;
	public RestoreSpeedThread(Game game) {
		super();
		this.game=game;
	}
	
	public void run(){
		try {
			sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		game.setSpeed(800);
	}
}
