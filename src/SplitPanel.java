import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SplitPanel extends JPanel{

		// what private data is needed?
		final Vector dimensions;
		SplitGameMap gm;
		private Timer t;
		BufferedImage img;
		//private Polygon map;

		public SplitPanel(int width, int length) {
			dimensions = new Vector(width, length);
			openBackgroundImg();
			
			//TODO Parth make background colors
			Color backgroundColor = Color.GREEN;
			this.setBackground(backgroundColor);
			
			
			beginGame();
		}

		private void openBackgroundImg() {
			try {
				img = ImageIO.read(new File("background.jpg"));
			}
			catch(IOException e) {
				System.out.println("Unable to instantiate background");
			}
			
		}

		private void beginGame() {
			this.setPreferredSize(new Dimension(dimensions.getX(), dimensions.getY()));
			createMap();
			startTicks();
			
		}

		private void startTicks() {
			t = new Timer(50, new ActionListener() {// fires off every 10 ms
				@Override
				public void actionPerformed(ActionEvent arg0) {
					gm.tick();// I tell the GameMap to tick... do what
						// you do every time the clock goes off.
					repaint();// naturally, we want to see the new view

					//This sketchy stuff requests a mouse location every frame so it repaints
			        //MouseInfo.getPointerInfo().getLocation();
				}
			});
			t.start();
		}

		private void createMap() {
			gm = new SplitGameMap(dimensions);
		}
		
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.drawImage(img, 0, 0, dimensions.getX(), dimensions.getY(), null);
			gm.draw(g);
		}
	
}
