import java.awt.Graphics;
import java.awt.Rectangle;

public class HorizontalDivider extends Divider {

	public HorizontalDivider(int x, int y, Polygon poly, Ball b,
			SplitGameMap gameMap) {
		this.gm = gameMap;
		this.ball = b;
		map = poly;
		location = new Vector(x, y);
		center = new Vector(map.getWidth() / 2 + 20, y);
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
		if(map.inside(location.getX(), location.getY()) && map.inside(location.getX() + length,  location.getY())) {
			length+= SPEED;
			location.setX(center.getX() - length / 2 + getPush());
		}
		else if(!map.inside(location.getX() - SPEED, location.getY())) {
			firstHit = true;
		}
		else if(!map.inside(location.getX() + length, location.getY())) {
			secondHit = true;
		}
		System.out.println(firstHit + "    " + secondHit);
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
		this.boundingRect = new Rectangle(location.getX(), location.getY(),
				length, DIMS);

	}

	@Override
	protected void dividerSplit() {
		Polygon newPolygon = map.split(this.location.getX(),
				this.location.getY(), this.location.getX() + length,
				this.location.getY(), ball.getX(), ball.getY(), this, null);
		gm.ready();
		gm.newSplit(newPolygon);
	}

}
