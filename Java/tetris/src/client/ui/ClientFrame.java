package client.ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JFrame;

import client.util.ImageUtil;

public class ClientFrame extends JFrame {
	private static final int WIDTH = 800, HEIGHT = 600;
	private boolean isDraging;
	private int x0;
	private int y0;

	public ClientFrame() {
		super();
	}

	public void initClientFrame() {
		// 设置拖动
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				isDraging = true;
				x0 = e.getX();
				y0 = e.getY();
			}

			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}

			public void mouseEntered(MouseEvent e) {
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						ImageUtil.getCursor().getImage(), new Point(),
						"MyCursor"));

			}

			public void mouseExited(MouseEvent e) {
				setCursor(Cursor.getDefaultCursor());
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				if (isDraging) {
					// getLocation() 返回：一个 Point 实例，表示在组件父级坐标空间中组件边界的左上角
					int x1 = getLocation().x;
					int y1 = getLocation().y;
					setLocation(x1 + e.getX() - x0, y1 + e.getY() - y0);
				}
			}
		});

		setUndecorated(true);
		this.setSize(WIDTH, HEIGHT);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width - WIDTH) / 2,
				(screenSize.height - HEIGHT) / 2);
		this.setResizable(false);
		setIconImage(ImageUtil.getIcon().getImage());
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
