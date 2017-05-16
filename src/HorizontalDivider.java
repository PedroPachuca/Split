import java.awt.Graphics;
import java.awt.Rectangle;


public class HorizontalDivider extends Divider{

	
	public HorizontalDivider(int x, int y, int centerX) {
		location = new Vector(x, y);
		center = new Vector(centerX, y);
		length = 0;
		boundingRect = new Rectangle(x, y, length, DIMS);
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
		if(Polygon.inside(location.getX(), location.getY())) {
		length+= SPEED;
		location.setX(center.getX() - length / 2);
		}
		else {
			stopGrowing = true;
		}
		
	}

		
	
	@Override
	protected void draw(Graphics g) {
		this.updateRect();
		g.fillRect(location.getX(), location.getY(), length, DIMS);
		
	}

	protected void updateRect() {
		this.boundingRect = new Rectangle(location.getX(), location.getY(), length, DIMS);
		
	}


}
