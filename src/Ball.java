
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball {
	
	private int x, y, radius;
	private BufferedImage img;
	
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
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void draw(Graphics g){
		g.drawImage(img, x, y, radius, radius, null);
	}
	
	public void bounce(){
		
	}
	
	public int getRandomXMovement(){
		//int x = (int)(Math.Random()*5)
		
		return x;
	}
}