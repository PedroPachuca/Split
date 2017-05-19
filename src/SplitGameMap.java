
import java.awt.Graphics;
import java.util.ArrayList;

public class SplitGameMap extends GameMap{

	private Ball ball;
	private Vector dimensions;
	private Polygon polygon = new Polygon();
	private ArrayList<Divider> dividers = new ArrayList<Divider>();
	private int cushion = 10;
	private boolean ready = true;
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
        //polygon = polygon.split(cushion*2+3,300,dimensions.getX()-cushion*3+3,300,700,100,new Wall(cushion*2, 300,dimensions.getX()-cushion*3+3-cushion*2,cushion),null);
		//updateAllPolygons();
	}
	
	private void updateAllPolygons() {
		ball.updatePolygon(polygon);
		for(Divider div: dividers) {
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
		ball = new Ball(dimensions.getX() / 3, dimensions.getY() / 3, ballRadius, polygon);
		
	}
	
	public void addDivider(Divider div) {
		if(ready) {
		dividers.clear();
		dividers.add(div);
		}
	}
	public Polygon getPolygon() {
		return polygon;
	}
	
	public void newSplit(Polygon newPolygon) {
		polygon = newPolygon;
		updateAllPolygons();
	}
}
