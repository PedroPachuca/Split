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
	private Rectangle boundingRect;
	private boolean alive = true;
	
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
	public void setAlive(boolean al) {
		alive = al;
	}
	public boolean getAlive() {
		return alive;
	}
	
	public void draw(Graphics g){
		if(alive) {
		this.updateRect();
		g.drawImage(img, x, y, params, params, null);
		}
	}
	
	public void bounce(){
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ArrayList<Wall> wallList = Polygon.walls();
		
		for(int i = 0; i < wallList.size(); i++){
			if(wallList.get(i).getWidth()==10){
				if(this.boundingRect.getMaxX()>wallList.get(i).getX() && this.boundingRect.getMaxX()<(wallList.get(i).getX()+wallList.get(i).getWidth())){//right wall
					a*=-1;
					x-=10;
					updateRect();
					
				}
				else if((this.boundingRect.getMinX()>wallList.get(i).getX()) && (this.boundingRect.getMinX()<wallList.get(i).getWidth()+wallList.get(i).getX())){ //left wall
					a*=-1;
					x+=10;
					updateRect();

				}
			}
			else if(wallList.get(i).getLength()==10){
				if((this.boundingRect.getMaxY()>wallList.get(i).getY()) && (this.boundingRect.getMaxY()<wallList.get(i).getLength()+wallList.get(i).getY())){ //bottom wall
					b*=-1;
					y-=10;
					updateRect();

				}		
				else if(this.boundingRect.getMinY()>wallList.get(i).getY() && this.boundingRect.getMinY()<wallList.get(i).getY()+wallList.get(i).getLength()){//top wall
					b*=-1;
					y+=10;
					updateRect();

				}
			}
			System.out.println("a="+a);
			System.out.println("b="+b);
		}
	}
	
	public int getRandomXMovement(){
		int x = 5;
		//int x = (int)(Math.random()*8)+1;		
		return x;
	}
	public int getRandomYMovement(){
		int y = 5;
		//int y = (int)(Math.random()*8)+1;		
		return y;
	}
	
	public void move() {
		this.x+=a;
		this.y+=b;
	}
	
	public void die(Graphics g){
	
	}
}