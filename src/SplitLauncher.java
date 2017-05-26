
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class SplitLauncher {

	private static Vector dimensions;
	public static int W = 800;
	public static int H = 600;
	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		dimensions = new Vector((int) screenSize.getWidth(), (int) screenSize.getHeight());
		
		new SplitLauncher().startGame();
	}

	private void startGame() {
		System.setProperty("sun.java2d.opengl", "true");
		JFrame splitFrame = new JFrame("SPLIT!");
		splitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SplitPanel splitPanel= new SplitPanel(dimensions.getX(), dimensions.getY());
		//SplitPanel splitPanel = new SplitPanel(W, H);
		splitFrame.add(splitPanel);
		splitFrame.pack();
		splitFrame.setVisible(true);
	}	
}