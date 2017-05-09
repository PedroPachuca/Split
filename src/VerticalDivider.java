import java.awt.Graphics;


public class VerticalDivider extends Divider {

	
	public VerticalDivider(int x, int y) {
		location = new Vector(x, y);
		length = 0;
	}
	@Override
	public void collided(Ball b) {
		//TODO
	}

	@Override
	protected void grow() {
		length += SPEED;
		
	}
	@Override
	protected void draw(Graphics g) {
		g.fillRect(location.getX(), location.getY(), length, DIMS);
		
	}

}
