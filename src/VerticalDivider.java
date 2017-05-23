import java.awt.Graphics;
import java.awt.Rectangle;


public class VerticalDivider extends Divider {

	
	public VerticalDivider(int x, int y, Polygon poly, Ball b, SplitGameMap gameMap) {
		this.gm = gameMap;
		this.ball = b;
		map = poly;
		location = new Vector(x, y);
		center = new Vector(x, poly.getHeight() / 2 + 20);
		length = 0;
		this.boundingRect = new Rectangle(x, y, DIMS, length);
	}
	@Override
	public void collided(Ball b) {
		if(getBoundingRect().intersects(b.getBoundingRect())) {
			if(!stopGrowing) {
				b.setAlive(false);
			}
		}
	}

	@Override
	protected void grow() {
		if(map.inside(location.getX(), location.getY())) {
		length += SPEED;
		location.setY(center.getY() - length / 2 + getPush());
		}
		else {
			if(!stopGrowing) {
				dividerSplit();
			}
			stopGrowing = true;
		}
	}
	@Override
	protected void draw(Graphics g) {
		this.updateRect();
		g.fillRect(location.getX(), location.getY(), DIMS, length);
		
	}
	@Override
	protected void updateRect() {
		this.boundingRect = new Rectangle(location.getX(), location.getY(), DIMS, length);
		
	}
	@Override
	protected void dividerSplit() {
		Polygon newPolygon = map.split(this.location.getX(), this.location.getY(), this.location.getX(), this.location.getY() + length, ball.getX(), ball.getY(), this, null);
		gm.newSplit(newPolygon);
	}
	
	private int getPush() {
		return map.getMinY(this.location.getX()) - 20;
	}

}
