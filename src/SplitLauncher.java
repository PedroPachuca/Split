
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SplitLauncher {

	private static Vector dimensions;
	public static int W, H;
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		dimensions = new Vector((int) screenSize.getWidth(), (int) screenSize.getHeight());
		int width = Integer.parseInt(JOptionPane.showInputDialog("Enter Width or 0 for Full Screen"));
		if(width != 0) {
			int height = Integer.parseInt(JOptionPane.showInputDialog("Enter Height or 0 for Full Screen"));
			W = width;
			H = height;
		}
		else {
			W = dimensions.getX();
			H = dimensions.getY();
		}
		
		new SplitLauncher().startGame();
	}

	private void startGame() {
		System.setProperty("sun.java2d.opengl", "true");
		JFrame splitFrame = new JFrame("SPLIT!");
		splitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SplitPanel splitPanel = new SplitPanel(W, H);
		splitFrame.add(splitPanel);
		splitFrame.pack();
		splitFrame.setVisible(true);
	}	
}