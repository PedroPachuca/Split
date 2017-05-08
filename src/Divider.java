import java.awt.Graphics;


public abstract class Divider implements Collidable {

	protected int length;
	protected Vector location;
	protected final int SPEED = 10;
	protected final int DIMS = 20;
	
	protected int getLength() {
		return length;
	}
	
	protected Divider getType() {
		return this.getType();
	}
	
	protected abstract void grow();
	
	protected abstract void draw(Graphics g);

}
