
public abstract class Divider implements Collidable {

	protected int length;
	protected Vector location;
	
	protected int getLength() {
		return length;
	}
	
	protected Divider getType() {
		return this.getType();
	}
	
	protected abstract void grow();
	
	protected abstract void draw();

}
