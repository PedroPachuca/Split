import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Divider implements Collidable {

	protected int length;
	protected Vector location;
	protected Vector center;
	protected final int SPEED = 10;
	protected final int DIMS = 10;
	protected Rectangle boundingRect;
	
	protected Rectangle getBoundingRect() {
		return boundingRect;
	}
	
	protected int getLength() {
		return length;
	}
	
	protected Divider getType() {
		return this.getType();
	}
	
	protected abstract void updateRect();
	
	protected abstract void grow();
	
	protected abstract void draw(Graphics g);

}
