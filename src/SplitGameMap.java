import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SplitGameMap extends GameMap{

	private Ball ball;
	private Vector dimensions;
	private ArrayList<Divider> dividers = new ArrayList<Divider>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private int cushion = 20;
	public SplitGameMap(Vector dims) {
		dimensions = dims;
		clearAndinitDividers();
		createBall();
		createWalls();
	}
	private void createWalls() {
		Wall topWall = new Wall(cushion, cushion, dimensions.getX() - cushion * 2, cushion);
		Wall bottomWall = new Wall(cushion, dimensions.getY() / 2 + cushion * 4, dimensions.getX() - cushion * 2, cushion);
		Wall leftWall = new Wall(cushion, cushion, cushion, dimensions.getY() / 2 + cushion * 4);
		Wall rightWall = new Wall(dimensions.getX() - cushion * 2, cushion, cushion, dimensions.getY() / 2 + cushion * 4);
		walls.add(rightWall);
		walls.add(leftWall);
		walls.add(bottomWall);
		walls.add(topWall);
		
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		ball.move();
		for(Wall w: walls) {
			w.collided(ball);
		}
		for(Divider div: dividers) {
			div.grow();
		}
	}

	@Override
	public void draw(Graphics g) {
		ball.draw(g);
		for(Divider div: dividers) {
			div.draw(g);
		}
		for(Wall w: walls) {
			w.draw(g);
		}
	}

	private void clearAndinitDividers() {
		dividers.clear();
		
		HorizontalDivider hd = new HorizontalDivider(0, 0);
		VerticalDivider vd = new VerticalDivider(0, 0);
		
		dividers.add(hd);
		dividers.add(vd);
	}
	
	private void createBall() {
		
		final int ballRadius = 100;
		ball = new Ball(dimensions.getX() / 2, dimensions.getY() / 2, ballRadius);
		
	}
}
