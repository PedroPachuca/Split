import java.awt.Graphics;
import java.awt.Rectangle;


public class VerticalDivider extends Divider {


	public VerticalDivider(int x, int y, Polygon poly, Ball b, SplitGameMap gameMap) {
		this.gm = gameMap;
		this.ball = b;
		map = poly;
		location = new Vector(x, y);
		//center = new Vector(x, poly.getHeight() / 2 + 20);
		center = new Vector(x, y);
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
		boolean firstHitInside = map.inside(location.getX(), location.getY());
		boolean secondHitInside = map.inside(location.getX(), location.getY() + length);
		int prospectiveLocation1 = location.getY() - SPEED;
		int prospectiveLocation2 = location.getY() + length + SPEED;
		if(firstHitInside && secondHitInside) {
			length+= SPEED;
			location.setY(center.getY() - length / 2 + getPush());
		}
		else if(!map.inside(location.getX(), prospectiveLocation1)) {
			leftHit = true;
		}
		else {
			length += SPEED;
			location.setY(location.getY() - SPEED + getPush());
		}
		if(!map.inside(location.getX(), prospectiveLocation2)) {
			rightHit = true;
		}
		else {
			length += SPEED;
		}
		if(leftHit && rightHit) {
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
		Polygon newPolygon = null;
		if(map.inside(this.location.getX(), this.location.getY())){
			newPolygon = map.split(this.location.getX(), this.location.getY() - SPEED, this.location.getX(), this.location.getY() + length, ball.getX(), ball.getY(), this, null);
		}
		else {
			newPolygon = map.split(this.location.getX(), this.location.getY(), this.location.getX(), this.location.getY() + length + SPEED, ball.getX(), ball.getY(), this, null);
		} 
		if(newPolygon != null) {
			gm.newSplit(newPolygon);	
		}
		gm.ready();
		leftHit = false;
		rightHit = false;
	}

	private int getPush() {
		return map.getMinY(this.location.getX()) - 20;
	}

}
