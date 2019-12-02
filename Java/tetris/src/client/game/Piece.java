package client.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.JComponent;

import client.util.ImageUtil;

public class Piece {
	protected Image image;
	protected int type;

	// 块
	public static final int O = 2, I = 3, L = 4, J = 5, T = 6, S = 7, Z = 8;// 2-8

	// 小块
	public static final int SO = 20, SI = 21, SL = 22, SJ = 23, ST = 24,
			SS = 25, SZ = 26, SDEAD = 27;// 20-27

	// 道具
	public static final int ADD1 = 10, ADD2 = 11, ADD3 = 12, SUB1 = 13,
			SUB2 = 14, SUB3 = 15, FAST = 16, SLOW = 17;// 10-17
	
	// 道具
	public static final int SADD1 = 28, SADD2 = 29, SADD3 = 30, SSUB1 = 31,
			SSUB2 = 32, SSUB3 = 33, SFAST = 34, SSLOW = 35;// 28-35

	public static final int GRAY = 18, DEAD = 9;

	public Piece() {

	}

	public Piece(int type) {
		this.type = type;
		if (type == O) {
			image = ImageUtil.getYellowSquare().getImage();
		} else if (type == I) {
			image = ImageUtil.getRedSquare().getImage();
		} else if (type == L) {
			image = ImageUtil.getLightGreenSquare().getImage();
		} else if (type == J) {
			image = ImageUtil.getLightBlueSquare().getImage();
		} else if (type == T) {
			image = ImageUtil.getPurpleSquare().getImage();
		} else if (type == S) {
			image = ImageUtil.getDarkGreenSquare().getImage();
		} else if (type == Z) {
			image = ImageUtil.getDarkBlueSquare().getImage();
		} else if (type == ADD1) {
			image = ImageUtil.getAdd1().getImage();
		} else if (type == ADD2) {
			image = ImageUtil.getAdd2().getImage();
		} else if (type == ADD3) {
			image = ImageUtil.getAdd3().getImage();
		} else if (type == SUB1) {
			image = ImageUtil.getSub1().getImage();
		} else if (type == SUB2) {
			image = ImageUtil.getSub2().getImage();
		} else if (type == SUB3) {
			image = ImageUtil.getSub3().getImage();
		} else if (type == FAST) {
			image = ImageUtil.getFast().getImage();
		} else if (type == SLOW) {
			image = ImageUtil.getSlow().getImage();
		} else if (type == GRAY) {
			image = ImageUtil.getGraySuqare().getImage();
		} else if (type == DEAD) {
			image = ImageUtil.getDeadSquare().getImage();
		} else if (type == SDEAD) {
			image = ImageUtil.getSDeadSquare().getImage();
		} else if (type == SO) {
			image = ImageUtil.getSYellowSquare().getImage();
		} else if (type == SI) {
			image = ImageUtil.getSRedSquare().getImage();
		} else if (type == SL) {
			image = ImageUtil.getSLightGreenSquare().getImage();
		} else if (type == SJ) {
			image = ImageUtil.getSLightBlueSquare().getImage();
		} else if (type == ST) {
			image = ImageUtil.getSPurpleSquare().getImage();
		} else if (type == SS) {
			image = ImageUtil.getSDarkGreenSquare().getImage();
		} else if (type == SZ) {
			image = ImageUtil.getSDarkBlueSquare().getImage();
		}else if (type == SADD1) {
			image = ImageUtil.getSAdd1().getImage();
		} else if (type == SADD2) {
			image = ImageUtil.getSAdd2().getImage();
		} else if (type == SADD3) {
			image = ImageUtil.getSAdd3().getImage();
		} else if (type == SSUB1) {
			image = ImageUtil.getSSub1().getImage();
		} else if (type == SSUB2) {
			image = ImageUtil.getSSub2().getImage();
		} else if (type == SSUB3) {
			image = ImageUtil.getSSub3().getImage();
		} else if (type == SFAST) {
			image = ImageUtil.getSFast().getImage();
		} else if (type == SSLOW) {
			image = ImageUtil.getSSlow().getImage();
		} 
	}

	public Piece(Image image) {
		this.image = image;
	}

	public Piece(int type, Image image) {
		this.type = type;
		this.image = image;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public void draw(Graphics g, int x, int y, JComponent component) {
		g.drawImage(image, x, y, component);

	}
}
