package client.game;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JComponent;


public class Block extends Piece {
	// 方块的变量
	private final static int SQUARE_SIZE = 16;

	private int block_cx, block_cy;

	private int blockBox[][] = new int[4][2]; // 保存该块的每一个坐标；

	private Game game;

	private static Random random = new Random();

	private final static int[][] O_BLOCK = { { 0, 0 }, { 1, 0 }, { 0, 1 },
			{ 1, 1 } };
	private final static int[][] I_BLOCK = { { -1, 0 }, { 0, 0 }, { 1, 0 },
			{ 2, 0 } };
	private final static int[][] L_BLOCK = { { -1, 1 }, { -1, 0 }, { 0, 0 },
			{ 1, 0 } };
	private final static int[][] J_BLOCK = { { 1, 1 }, { 1, 0 }, { 0, 0 },
			{ -1, 0 } };
	private final static int[][] T_BLOCK = { { 0, -1 }, { -1, 0 }, { 0, 0 },
			{ 1, 0 } };
	private final static int[][] S_BLOCK = { { -1, 0 }, { 0, 0 }, { 0, -1 },
			{ 1, -1 } };
	private final static int[][] Z_BLOCK = { { -1, -1 }, { 0, -1 }, { 0, 0 },
			{ 1, 0 } };

	private final static int[][][] boxtypes = { O_BLOCK, I_BLOCK, J_BLOCK,
			L_BLOCK, T_BLOCK, S_BLOCK, Z_BLOCK };

	public Block() {

	}

	public Block(int type) {
		super(type);
		if (type == O) {
			blockBox = O_BLOCK;
		} else if (type == I) {
			blockBox = I_BLOCK;
		} else if (type == L) {
			blockBox = L_BLOCK;
		} else if (type == J) {
			blockBox = J_BLOCK;
		} else if (type == T) {
			blockBox = T_BLOCK;
		} else if (type == S) {
			blockBox = S_BLOCK;
		} else if (type == Z) {
			blockBox = Z_BLOCK;
		}
	}

	public static Block getRandomBlock() {
		int type = random.nextInt(7) + 2;
		return new Block(type);
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Game getGameInfo() {
		return game;
	}

	public int getBlock_cx() {
		return block_cx;
	}

	public void setBlock_cx(int block_cx) {
		this.block_cx = block_cx;
	}

	public int getBlock_cy() {
		return block_cy;
	}

	public void setBlock_cy(int block_cy) {
		this.block_cy = block_cy;
	}

	public int[][] getBlockBox() {
		return blockBox;
	}

	public void drawBlock(Graphics g, int x, int y, JComponent component) {
		for (int i = 0; i < 4; i++) {
			int x1 = x + blockBox[i][0] * SQUARE_SIZE;
			int y1 = y + blockBox[i][1] * SQUARE_SIZE;
			g.drawImage(image, x1, y1, component);
		}
	}

	public void drawBlock(Graphics g, int x, int y, Image image,
			JComponent component) {
		for (int i = 0; i < 4; i++) {
			int x1 = x + blockBox[i][0] * SQUARE_SIZE;
			int y1 = y + blockBox[i][1] * SQUARE_SIZE;
			g.drawImage(image, x1, y1, component);
		}
	}

	public void drawBlock(Graphics g, JComponent component) {
		for (int i = 0; i < 4; i++) {
			int x = (block_cx + blockBox[i][0]) * SQUARE_SIZE;
			int y = (block_cy + blockBox[i][1]) * SQUARE_SIZE;
			g.drawImage(image, x, y, component);
		}
	}

	public int getMaxX() {
		int x = 0;
		for (int i = 0; i < 4; i++) {
			if (x < block_cx + blockBox[i][0])
				x = block_cx + blockBox[i][0];
		}
		return x;
	}

	public int getMaxY() {
		int y = 0;
		for (int i = 0; i < 4; i++) {
			if (y < block_cx + blockBox[i][1])
				y = block_cy + blockBox[i][1];
		}
		return y;
	}

	synchronized public void moveDown() {
		// 保存变换后坐标的变量
		int box[][] = new int[4][2]; /* 四个正方形的坐标（相对方块中心点） */
		int cx, cy; /* 方块中心点在游戏空间中的坐标（相对游戏空间左上角点） */
		cx = block_cx;
		cy = block_cy;
		for (int i = 0; i < 4; i++) // 计算变换后的各正方形坐标
		{
			box[i][0] = blockBox[i][0]; // 用公式x1=x0计算正方形的x 坐标
			box[i][1] = blockBox[i][1] + 1; // 用公式y1=y0+1计算正方形的y 坐标
		}
		if (canChangeTo(cx, cy, box)) {// 如果能变换到些位置，则更新方块的坐标数据
			block_cx = cx;
			block_cy = cy + 1;
		}
	}/* 下移动方块 */

	// 直接下落到底
	synchronized public void getDown() {
		while (!isHitBottom()) {
			moveDown();
		}
	}

	public void moveLeft() {
		// 保存变换后坐标的变量
		int box[][] = new int[4][2]; /* 四个正方形的坐标（相对方块中心点） */
		int cx, cy; /* 方块中心点在游戏空间中的坐标（相对游戏空间左上角点） */
		cx = block_cx;
		cy = block_cy;
		for (int i = 0; i < 4; i++) // 计算变换后的各正方形坐标
		{
			box[i][0] = blockBox[i][0] - 1; // 用公式x1=x0-1计算正方形的x 坐标
			box[i][1] = blockBox[i][1]; // 用公式y1=y0计算正方形的y 坐标
		}
		if (canChangeTo(cx, cy, box)) {// 如果能变换到些位置，则更新方块的坐标数据
			block_cx = cx - 1;
			block_cy = cy;
		}
	}/* 左移动方块 */

	public void moveRight() {
		// 保存变换后坐标的变量
		int box[][] = new int[4][2]; /* 四个正方形的坐标（相对方块中心点） */
		int cx, cy; /* 方块中心点在游戏空间中的坐标（相对游戏空间左上角点） */
		cx = block_cx;
		cy = block_cy;
		for (int i = 0; i < 4; i++) // 计算变换后的各正方形坐标
		{
			box[i][0] = blockBox[i][0] + 1; // 用公式x1=x0+1计算正方形的x 坐标
			box[i][1] = blockBox[i][1]; // 用公式y1=y0+计算正方形的y 坐标
		}
		if (canChangeTo(cx, cy, box)) {// 如果能变换到些位置，则更新方块的坐标数据
			block_cx = cx + 1;
			block_cy = cy;
		}
	}/* 右移动方块 */

	public void turnLeft() {
		if (type != O) {
			// 保存变换后坐标的变量
			int box[][] = new int[4][2]; /* 四个正方形的坐标（相对方块中心点） */
			int cx, cy; /* 方块中心点在游戏空间中的坐标（相对游戏空间左上角点） */
			cx = block_cx;
			cy = block_cy; // 中心位置不变
			for (int i = 0; i < 4; i++) // 计算变换后的各正方形坐标
			{
				box[i][0] = -blockBox[i][1]; // 用公式x1=-y0计算正方形的x 坐标
				box[i][1] = blockBox[i][0]; // 用公式y1=x0计算正方形的y 坐标
			}
			if (canChangeTo(cx, cy, box)) {// 如果能变换到些位置，则更新方块的坐标数据
				block_cx = cx;
				block_cy = cy;
				blockBox = box;
			}
		}
	}/* 左转动方块 */

	public void turnRight() {
		if (type != O) {
			// 保存变换后坐标的变量
			int box[][] = new int[4][2]; /* 四个正方形的坐标（相对方块中心点） */
			int cx, cy; /* 方块中心点在游戏空间中的坐标（相对游戏空间左上角点） */
			cx = block_cx;
			cy = block_cy; // 中心位置不变
			for (int i = 0; i < 4; i++) // 计算变换后的各正方形坐标
			{
				box[i][0] = blockBox[i][1]; // 用公式x1=y0计算正方形的x 坐标
				box[i][1] = -blockBox[i][0]; // 用公式y1=-x0计算正方形的y 坐标
			}
			if (canChangeTo(cx, cy, box)) {// 如果能变换到些位置，则更新方块的坐标数据
				block_cx = cx;
				block_cy = cy;
				blockBox = box;
			}
		}
	}/* 右转动方块 */

	public boolean isAllInTetrisSpace() {
		return canChangeTo(block_cx, block_cy, blockBox);
	}

	private boolean canChangeTo(int cx, int cy, int box[][]) {
		boolean canChangeTo = true; /* 标志变量，假设为能进行指定变换 */
		int x, y;
		for (int i = 0; i < 4; i++) {
			x = cx + box[i][0];
			y = cy + box[i][1];
			if (x < 0 || y < 0 || x >= Game.WIDTH || y >= Game.HEIGHT
					|| (y > 0 && game.tetrisSpace[y][x] != 0)) {
				canChangeTo = false;
				break;
			} /* 设置为不能进行指定变换 */
		}
		return canChangeTo;
	}// 判断方块能否走到这位置

	public boolean isHitBottom() {
		boolean isHitBottom = true;
		// 保存变换后坐标的变量
		int box[][] = new int[4][2]; /* 四个正方形的坐标（相对方块中心点） */
		int cx, cy; /* 方块中心点在游戏空间中的坐标（相对游戏空间左上角点） */
		cx = block_cx;
		cy = block_cy;
		for (int i = 0; i < 4; i++) // 计算变换后的各正方形坐标
		{
			box[i][0] = blockBox[i][0]; // 用公式x1=x0计算正方形的x 坐标
			box[i][1] = blockBox[i][1] + 1; // 用公式y1=y0+1计算正方形的y 坐标
		}
		if (canChangeTo(cx, cy, box)) {// 如果能变换到些位置，则更新方块的坐标数据
			isHitBottom = false;
		}
		return isHitBottom;
	} // 判断当前方块是否已触底

	public boolean isHitTop() {

		boolean isHitTop = true;
		if (block_cy != 1 && block_cy != 0)
			isHitTop = false;
		// 保存变换后坐标的变量1
		int box[][] = new int[4][2]; /* 四个正方形的坐标（相对方块中心点） */
		int cx, cy; /* 方块中心点在游戏空间中的坐标（相对游戏空间左上角点） */
		cx = block_cx;
		cy = block_cy;
		for (int i = 0; i < 4; i++) // 计算变换后的各正方形坐标
		{
			box[i][0] = blockBox[i][0]; // 用公式x1=x0计算正方形的x 坐标
			box[i][1] = blockBox[i][1] - 1; // 用公式y1=y0-1计算正方形的y 坐标
		}
		if (canChangeTo(cx, cy, box)) {
			isHitTop = false;
		}
		return isHitTop;
	}

}
