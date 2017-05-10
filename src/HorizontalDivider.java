import java.awt.Graphics;
import java.awt.Rectangle;


public class HorizontalDivider extends Divider{

	
	public HorizontalDivider(int x, int y) {
		location = new Vector(x, y);
		length = 0;
		boundingRect = new Rectangle(x, y, DIMS, length);
	}
	
	@Override
	public void collided(Ball b) {
		if(getBoundingRect().intersects(b.getBoundingRect())) {
			b.bounce();
		}
	}

	@Override
	protected void grow() {
		length+= SPEED;
	}

	@Override
	protected void draw(Graphics g) {
		this.updateRect();
		g.fillRect(location.getX(), location.getY(), DIMS, length);
		
	}

	protected void updateRect() {
		this.boundingRect = new Rectangle(location.getX(), location.getY(), DIMS, length);
		
	}


}
