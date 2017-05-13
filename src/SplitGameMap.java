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
	private Polygon  polygon = new Polygon();
	private ArrayList<Divider> dividers = new ArrayList<Divider>();
	private int cushion = 10;
	public SplitGameMap(Vector dims) {
		dimensions = dims;
		createBall();
		createWalls();

	}
	private void createWalls() {

		polygon.add(new Wall(cushion*2,cushion*2,cushion,dimensions.getY()-150-2*cushion));
        polygon.add(new Wall(cushion*2,dimensions.getY()-150-cushion,dimensions.getX()-4*cushion,cushion));
        polygon.add(new Wall(cushion*2,cushion*2,dimensions.getX()-4*cushion,cushion));
        polygon.add(new Wall(dimensions.getX()-cushion*3,cushion*2,cushion,dimensions.getY()-150-2*cushion));
        //polygon=polygon.split(500,cushion*2+3,500,cushion*2+dimensions.getY()-150-2*cushion-3,490,300,new Wall(500
        //		,cushion*2,cushion,dimensions.getY()-150-2*cushion),null);
		
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		ball.move();
		for(Wall w: polygon.walls()) {
			w.collided(ball);
		}
		for(Divider div: dividers) {
			div.grow();
			div.collided(ball);
		}
	}

	@Override
	public void draw(Graphics g) {
		ball.draw(g);
		for(Divider div: dividers) {
			div.draw(g);
		}
		for(Wall w: polygon.walls()) {
			w.draw(g);
		}
	}
	
	private void createBall() {
		
		final int ballRadius = 100;
		ball = new Ball(dimensions.getX() / 3, dimensions.getY() / 3, ballRadius);
		
	}
	
	public void addWall(Divider div) {
		if(dividers.isEmpty()) {
		dividers.add(div);
		}
	}
}
