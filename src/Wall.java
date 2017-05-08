import java.awt.Graphics;

public class Wall implements Collidable{

	private int x1,x2,y1,y2;
	private int x, y, width, length;
	public Wall(int x, int y, int width, int length){
		this.x = x;
		this.y = y;
		this.width = width;
		this.length = length;
		
	}
	
	public int getX1(){
		return x1;
		
	}
	public int getX2(){
		return x2;
		
	}
	public int getY1(){
		return y1;
	
	}
	public int getY2(){
		return y2;
	
	}

	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
}
	public int getWidth() {
		return width;
	}
	
	public int getLengtht() {
		return length;
	}

	public void draw(Graphics g){
		//g.drawLine(x1,  x2,  y1,  y2);
		g.fillRect(x,  y,  width,  length);
		System.out.println("Wall drawn");
	}

	public boolean collided(Ball b) {
		if(b.getX() == x && b.getY() == y){
			b.bounce();
			return true;
		}
		return false;
		
	}

}
