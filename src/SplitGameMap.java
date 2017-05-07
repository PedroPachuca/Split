import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SplitGameMap extends GameMap{

	private Color backgroundColor;
	private Ball ball;
	private Vector dimensions;
	private ArrayList<Divider> dividers = new ArrayList<Divider>();
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	
	public SplitGameMap(Vector dims) {
		dimensions = dims;
		clearAndinitDividers();
		createBall();
	}
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		ball.draw(g);
		for(Divider div: dividers) {
			div.draw();
		}
		for(Wall w: walls) {
			w.draw();
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
		
		final int ballRadius = 2;
		ball = new Ball(0, 0, ballRadius);
		
	}
}
