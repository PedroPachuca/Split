
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball {
	
	private int x, y, radius;
	private BufferedImage img;
	private int a = getRandomXMovement();
	private int b = getRandomYMovement();
	
	public Ball(int x, int y, int radius){
		this.setX(x);
		this.setY(y);
		this.radius=radius;
		getImg();
		
	}
	
	private void getImg() {
		
		try {
			img = ImageIO.read(new File("ball.png"));
		}
		catch(IOException e) {
			System.out.println("Unable to instantiate image");
		}
		
	}

	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setA(){
		a = getRandomXMovement();
	}
	
	public void setB(){
		b = getRandomYMovement();
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, radius / 2, radius / 2, null);
		System.out.println("Ball drawn");
	}
	
	public void bounce(){
		
	}
	
	public int getRandomXMovement(){
		int x = (int)(Math.random()*5)+1;		
		return x;
	}
	public int getRandomYMovement(){
		int y = (int)(Math.random()*5)+1;		
		return y;
	}
	
	public void move() {
		x = a + this.x;
		y = b + this.y;
	}
}