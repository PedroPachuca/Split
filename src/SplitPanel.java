import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;


public class SplitPanel extends JPanel{

		// what private data is needed?
		final int width, length;
		private Color backgroundColor;
		private Ball ball;
		//private Polygon map;
		private ArrayList<Divider> dividers = new ArrayList<Divider>();

		public SplitPanel(int width, int length) {
			this.width = width;
			this.length = length;
			
			//TODO Parth make background colors
			//backgroundColor = new Color();
			
			
			beginGame();
		}

		private void beginGame() {
			
			createBall();
			createMap();
			initDividers();
			
		}

		private void initDividers() {
			// TODO Auto-generated method stub
			
		}

		private void createMap() {
			// TODO Auto-generated method stub
			
		}

		private void createBall() {
			final int ballRadius = 2;
			ball = new Ball(0, 0, ballRadius);
			
		}
		
	

	
}
