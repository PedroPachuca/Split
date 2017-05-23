import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Ball {

	private int x, y;
	static int radius;
	private int params;
	private BufferedImage img;
	private int a = getRandomXMovement();
	private int b = getRandomYMovement();
	private Rectangle boundingRect;
	private boolean alive = true;
	private Polygon map;
	private final int SPEED = 4;

	public Ball(int x, int y, int radius, Polygon poly) {
		map = poly;
		this.setX(x);
		this.setY(y);
		this.radius = radius;
		getImg();
		params = radius / 2;
		boundingRect = new Rectangle(x, y, params, params);
	}

	private void getImg() {

		try {
			img = ImageIO.read(new File("src/ball.png"));
		} catch (IOException e) {
			System.out.println("Unable to instantiate image");
		}
	}

	public void setSpeed(int level) {
		if (a > -10 && a < 10) {
			if (a < 0) {
				a = 0 - SPEED - 2 * level;
			}
			if (a > 0) {
				a = 0 + SPEED + 2 * level;
			}
		}
		if (b > -10 && b < 10) {
			if (b < 0) {
				b = 0 - SPEED - 2 * level;
			}
			if (b > 0) {
				b = 0 + SPEED + 2 * level;
			}
		}
	}

	public int getSquareParams() {
		return params;
	}

	public void updatePolygon(Polygon newMap) {
		map = newMap;
	}

	private void updateRect() {
		this.boundingRect = new Rectangle(x, y, params, params);
	}

	public Rectangle getBoundingRect() {
		return boundingRect;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setA() {
		a = getRandomXMovement();
	}

	public void setB() {
		b = getRandomYMovement();
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setAlive(boolean al) {
		alive = al;
	}

	public boolean getAlive() {
		return alive;
	}

	public void draw(Graphics g) {
		if (alive) {
			this.updateRect();
			g.drawImage(img, x, y, params, params, null);
		}
	}

	public void bounce() {
		// Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ArrayList<Wall> wallList = map.walls();

		for (int i = 0; i < wallList.size(); i++) {
			if (wallList.get(i).getWidth() == 10) {
				if (this.boundingRect.getMaxX() > wallList.get(i).getX()
						&& this.boundingRect.getMaxX() < (wallList.get(i).getX() + wallList.get(i).getWidth())) {// right
					// wall
					if (a > 0)
						a *= -1;
					x -= 10;
					updateRect();
				}
				if ((this.boundingRect.getMinX() > wallList.get(i).getX())
						&& (this.boundingRect.getMinX() < wallList.get(i).getWidth() + wallList.get(i).getX())) { // left
					// wall
					if (a < 0)
						a *= -1;
					x += 10;
					updateRect();

				}
			}
			if (wallList.get(i).getLength() == 10) {
				if ((this.boundingRect.getMaxY() > wallList.get(i).getY())
						&& (this.boundingRect.getMaxY() < wallList.get(i).getLength() + wallList.get(i).getY())) { // bottom
					// wall
					if (b > 0)
						b *= -1;
					y -= 10;
					updateRect();

				}
				if (this.boundingRect.getMinY() > wallList.get(i).getY()
						&& this.boundingRect.getMinY() < wallList.get(i).getY() + wallList.get(i).getLength()) {// top
					// wall
					if (b < 0)
						b *= -1;
					y += 10;
					updateRect();

				}
			}
		}
	}

	public int getRandomXMovement() {
//		int x = (int) (Math.random() * 8) + 1;
		return SPEED;
	}

	public int getRandomYMovement() {
//		int y = (int) (Math.random() * 8) + 1;
		return SPEED;
	}

	public void move() {
		this.x += a;
		this.y += b;
	}

	public void die(Graphics g) {

	}
}