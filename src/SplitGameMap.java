
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SplitGameMap extends GameMap {

	private Ball ball;
	private Vector dimensions;
	private Polygon polygon = new Polygon();
	private ArrayList<Divider> dividers = new ArrayList<Divider>();
	private int cushion = 10;
	private boolean ready = true;
	private boolean localReady = true;
	private Image gameOverImage;
	private final int STARTWIDTH;
	private final int STARTHEIGHT;

	public SplitGameMap(Vector dims) {
		dimensions = dims;
		createBall();
		createWalls();
		STARTHEIGHT = polygon.getHeight();
		STARTWIDTH = polygon.getWidth();
	}
	
	public int getStartWidth() {
		return STARTWIDTH;
	}
	
	public int getStartHeight() {
		return STARTHEIGHT;
	}
	
	private void createWalls() {

		polygon.add(new Wall(cushion * 2, cushion * 2, cushion, dimensions.getY() - 150 - 2 * cushion));
		polygon.add(new Wall(cushion * 2, dimensions.getY() - 150 - cushion, dimensions.getX() - 4 * cushion, cushion));
		polygon.add(new Wall(cushion * 2, cushion * 2, dimensions.getX() - 4 * cushion, cushion));
		polygon.add(
				new Wall(dimensions.getX() - cushion * 3, cushion * 2, cushion, dimensions.getY() - 150 - 2 * cushion));
		
		// polygon =
		// polygon.split(cushion*2+3,300,dimensions.getX()-cushion*3+3,300,700,100,new
		// Wall(cushion*2,
		// 300,dimensions.getX()-cushion*3+3-cushion*2,cushion),null);
		// updateAllPolygons();
	}

	private void updateAllPolygons() {
		ball.updatePolygon(polygon);
		for (Divider div : dividers) {
			div.updatePolygon(polygon);
		}
	}

	public Ball getBall() {
		return ball;
	}

	public void ready() {
		ready = true;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		ball.move();
		for (Wall w : polygon.walls()) {
			w.collided(ball);
		}
		for (Divider div : dividers) {
			div.grow();
			div.collided(ball);
		}
	}

	@Override
	public void draw(Graphics g) {
		if (ball.getAlive()) {
			ball.draw(g);
			for (Divider div : dividers) {
				div.draw(g);
			}
			for (Wall w : polygon.walls()) {
				w.draw(g);
			}
		} 
		else {
			gameOver();
		}
	}

	private void createBall() {
		final int ballRadius = 100;
		int x = (int) (Math.random()*(dimensions.getX()-cushion*6-100))+cushion*3;
		int y = (int) (Math.random()*(dimensions.getY()-150-cushion-cushion*3-100))+cushion*3;
		ball = new Ball(x, y, ballRadius, polygon);
		System.out.println("x = "+x);
		System.out.println("y = "+y);
	}

	public void addDivider(Divider div) {
		if (ready && localReady) {
			dividers.clear();
			dividers.add(div);
			localReady = false;
		}
	}

	public Polygon getPolygon() {
		return polygon;
	}

	public void newSplit(Polygon newPolygon) {
		localReady = true;
		polygon = newPolygon;
		updateAllPolygons();
	}

	public boolean getGameOver() {
		return gameOver;
	}
}
