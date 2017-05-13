import java.awt.Graphics;
import java.awt.Rectangle;


public class HorizontalDivider extends Divider{

	
	public HorizontalDivider(int x, int y) {
		location = new Vector(x, y);
		center = new Vector(x, y);
		length = 0;
		boundingRect = new Rectangle(x, y, length, DIMS);
	}
	
	@Override
	public void collided(Ball b) {
		if(getBoundingRect().intersects(b.getBoundingRect())) {
			//TODO rip
			System.out.println("ball hit a divider");
		}
	}

	@Override
	protected void grow() {
		if(Polygon.inside(location.getX(), location.getY())) {
		length+= SPEED;
		location.setX(center.getX() - length / 2);
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
