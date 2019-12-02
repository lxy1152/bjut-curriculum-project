package client.game;

import java.io.Serializable;

import client.util.AudioManager;
import commons.User;

//道具类

public class Prop extends Piece implements Serializable{

	private Game game;

	public Prop(int type) {
		super(type);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public void use() {
		if (type == ADD1) {
			game.addDeadLine(1);
		} else if (type == ADD2) {
			game.addDeadLine(2);
		} else if (type == ADD3) {
			game.addDeadLine(3);
		}else if(type==SUB1){
			game.subLine(1);
		}else if(type==SUB2){
			game.subLine(2);
		}else if(type==SUB3){
			game.subLine(3);
		}else if(type==FAST){
			game.setSpeed(100);
			new RestoreSpeedThread(game).start();
		}else if(type==SLOW){
			game.setSpeed(500);
		}
		AudioManager.playUsePropSound();
	}
}
