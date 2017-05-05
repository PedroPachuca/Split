import java.awt.Graphics;

public class Ball {
	
	private int x, y, radius;
	
	public Ball(int x, int y, int radius){
		this.setX(x);
		this.setY(y);
		this.radius=radius;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void draw(int x, int y){
		Graphics g;
	}
	
	public void bounce(){
		
	}
	
	public int getRandomXMovement(){
		//int x = (int)(Math.Random()*5)
		
		return x;
	}
}