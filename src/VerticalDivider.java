import java.awt.Graphics;
import java.awt.Rectangle;


public class VerticalDivider extends Divider {

	
	public VerticalDivider(int x, int y, int centerY, Polygon poly) {
		map = poly;
		location = new Vector(x, y);
		center = new Vector(x, centerY);
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
		location.setY(center.getY() - length / 2);
		}
		else {
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

}
