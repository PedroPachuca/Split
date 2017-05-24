import java.awt.Graphics;
import java.awt.Rectangle;

public class HorizontalDivider extends Divider {

	public HorizontalDivider(int x, int y, Polygon poly, Ball b,
			SplitGameMap gameMap) {
		this.gm = gameMap;
		this.ball = b;
		map = poly;
		location = new Vector(x, y);
		center = new Vector(x, y);
		length = 0;
		boundingRect = new Rectangle(x, y, length, DIMS);
	}

	@Override
	public void collided(Ball b) {
		if (getBoundingRect().intersects(b.getBoundingRect())) {
			if (!stopGrowing) {
				b.setAlive(false);
			}
		}
	}

	@Override
	protected void grow() {	
		boolean firstHitInside = map.inside(location.getX(), location.getY());
		boolean secondHitInside = map.inside(location.getX() + length, location.getY());
		int prospectiveLocation1 = location.getX() - SPEED;
		int prospectiveLocation2 = location.getX() + length + SPEED;
		if(firstHitInside && secondHitInside) {
			length+= SPEED;
			location.setX(center.getX() - length / 2 + getPush());
		}
		else if(!map.inside(prospectiveLocation1, location.getY())) {
			firstHit = true;
		}
		else {
			length += SPEED;
			location.setX(location.getX() - SPEED + getPush());
		}
		if(!map.inside(prospectiveLocation2, location.getY())) {
			secondHit = true;
		}
		else {
			length += SPEED;
		}
		if(firstHit && secondHit) {
			if(!stopGrowing) {
				dividerSplit();
			}
			stopGrowing = true;
		}
	}

	private int getPush() {
		return map.getMinX(this.location.getY()) - 20;
	}

	@Override
	protected void draw(Graphics g) {
		this.updateRect();
		g.fillRect(location.getX(), location.getY(), length, DIMS);
	}

	protected void updateRect() {
		this.boundingRect = new Rectangle(location.getX(), location.getY(), length, DIMS);

	}

	@Override
	protected void dividerSplit() {
		Polygon newPolygon = null;
		if(map.inside(this.location.getX(), this.location.getY())){
			newPolygon = map.split(this.location.getX() - SPEED, this.location.getY(), this.location.getX(), this.location.getY() + length, ball.getX(), ball.getY(), this, null);
		}
		else {
			newPolygon = map.split(this.location.getX(), this.location.getY(), this.location.getX() + length + SPEED, this.location.getY(), ball.getX(), ball.getY(), this, null);
		} 
		if(newPolygon != null) {
			gm.newSplit(newPolygon);	
		}
		gm.ready();
		firstHit = false;
		secondHit = false;
	}

}
