import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

public class SplitPanel extends JPanel{

		// what private data is needed?
		final Vector dimensions;
		SplitGameMap gm;
		private Timer t;
		BufferedImage img;
		private BufferedImage vertical, horizontal;
		private int level;
		private int areaAvailable;
		private JProgressBar progressBar;
		private int areaCutOff;
		private String typeOfDivider;
		//private Polygon map;

		public SplitPanel(int width, int length) {
			this.setLayout(new BorderLayout());
			dimensions = new Vector(width, length);
			openBackgroundImg();
			progressBar = new JProgressBar();
			level=1;
			areaAvailable = length * width;
			
			
			//TODO Parth make background colors
			Color backgroundColor = Color.GREEN;
			this.setBackground(backgroundColor);
			
			
			beginGame();
		}

		private void openBackgroundImg() {
			try {
				img = ImageIO.read(new File("src/background.jpg"));
			}
			catch(IOException e) {
				System.out.println("Unable to instantiate background");
			}
			
		}
		
		private void setUpProgressBar(){
			
		}

		private void beginGame() {
			this.setPreferredSize(new Dimension(dimensions.getX(), dimensions.getY()));
			createMap();
			createDividerButtons();
			startTicks();
			
		}

		private void createDividerButtons() {
			openImages();
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout());
			
			JButton horizontalDividerButton = new JButton();
			JButton verticalDividerButton = new JButton();
			
			horizontalDividerButton.setIcon(new ImageIcon(horizontal));
			verticalDividerButton.setIcon(new ImageIcon(vertical));
			
			horizontalDividerButton.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					typeOfDivider = "horizontal";
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			verticalDividerButton.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					typeOfDivider = "vertical";
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			buttonPanel.add(horizontalDividerButton);
			buttonPanel.add(verticalDividerButton);
			
			buttonPanel.setBackground(Color.CYAN);
			//TODO PICK COLOR
			
			this.add(buttonPanel,BorderLayout.SOUTH);
		}

		private void openImages() {
			try {
				horizontal = ImageIO.read(new File("src/hoirzontal.png"));
			}
			catch(IOException e) {
				System.out.println("Unable to instantiate horiozntal");
			}
			try {
				vertical = ImageIO.read(new File("src/vertical.png"));
			}
			catch(IOException e) {
				System.out.println("Unable to instantiate vertical");
			}
			
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
