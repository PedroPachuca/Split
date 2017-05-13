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
	private int a = getRandomXMovement()*-1;
	private int b = getRandomYMovement()*-1;
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
			img = ImageIO.read(new File("src/ball.png"));
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
		
		if((this.boundingRect.getMinX()>20) && (this.boundingRect.getMinX()<30)){ //left wall
			a*=-1;
		}
		else if((this.boundingRect.getMaxY()>(600-150-10)) && (this.boundingRect.getMaxY()<(600))){ //bottom wall
			b*=-1;
		}
		else if(this.boundingRect.getMinY()>20 && this.boundingRect.getMinY()<30){//top wall
			b*=-1;
		}
		else if(this.boundingRect.getMaxX()>(800-30) && this.boundingRect.getMaxX()<(800)){//right wall
			a*=-1;
		}

		move();
		move();
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
		this.x+=a;
		this.y+=b;
	}
	
	public void die(Graphics g){
	
	}
}