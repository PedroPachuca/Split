import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Ball {
	
	private int x, y, radius, params;
	private BufferedImage img;
	private int a = getRandomXMovement();
	private int b = getRandomYMovement();
	private int c = 0;
	private Rectangle boundingRect;
	
	public Ball(int x, int y, int radius){
		this.setX(x);
		this.setY(y);
		this.radius=radius;
		getImg();
		params = radius / 2;
		boundingRect = new Rectangle(x, y, params, params);
	}
	
	private void getImg() {
		
		try {
			img = ImageIO.read(new File("ball.png"));
		}
		catch(IOException e) {
			System.out.println("Unable to instantiate image");
		}
		
	}
	public int getSquareParams() {
		return params;
	}
	
	private void updateRect() {
		this.boundingRect = new Rectangle(x, y, params, params);
	}
	public Rectangle getBoundingRect() {
		return boundingRect;
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
		this.updateRect();
		g.drawImage(img, x, y, params, params, null);
	}
	
	public void bounce(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//ArrayList<Wall> w = new ArrayList<>();
		
		if((x<200 && x>0) || (x<900 && x>(screenSize.getWidth()-200))){
			a*=-1;
		}
		else{
			b*=-1;
		}
		move();
		move();
		
//		if(c*2==0)
//			b*=-1;
//		else
//			a*=-1;
//		c++;
		System.out.println("ball bounced");
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
	
	public void die(Graphics g){
	
	}
}