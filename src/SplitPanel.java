import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;

public class SplitPanel extends JPanel {

	final Vector dimensions;
	SplitGameMap gm;
	private Timer t;
	BufferedImage img;
	private BufferedImage vertical, horizontal;
	private int level;
	private int areaAvailable;
	private JProgressBar progressBar;
	private int areaCutOff;
	private String typeOfDivider = null;
	JTextField levelsField;
	JTextField l;
	private final int startingAreaAvailable;
	private Image gameOverImage, playAgainButton;
	private JPanel buttonPanel;
	private JPanel iAmDead;
	private JButton horizontalDividerButton;
	private JButton verticalDividerButton;

	public SplitPanel(int width, int length) {
		this.setLayout(new BorderLayout());
		dimensions = new Vector(width, length);
		openBackgroundImg();
		Color backgroundColor = Color.GREEN;
		this.setBackground(backgroundColor);

		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent click) {
				Divider div = null;
				if (gm.getPolygon().inside(click.getX(), click.getY())) {
					if (typeOfDivider != null) {
						if (typeOfDivider.equals("vertical")) {
							div = new VerticalDivider(click.getX(), click.getY(), gm.getPolygon(), gm.getBall(), gm);
						} else {
							div = new HorizontalDivider(click.getX(), click.getY(), gm.getPolygon(), gm.getBall(), gm);
						}
					}
				}
				if (div != null) {
					gm.addDivider(div);
				}
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {}});
		level = 1;
		beginGame();
		startingAreaAvailable = gm.getPolygon().getHeight() * gm.getPolygon().getWidth();
		areaAvailable = gm.getPolygon().getHeight() * gm.getPolygon().getWidth();
		progressBar = new JProgressBar();
		setUpProgressBar(width, length);
		areaCutOff = 0;
	}
	private void openBackgroundImg() {
		try {
			img = ImageIO.read(new File("src/background.jpg"));
		} catch (IOException e) {
			System.out.println("Unable to instantiate background");
		}
	}
	private void setUpProgressBar(int width, int length) {
		JPanel barPanel = new JPanel();
		barPanel.setLayout(new GridLayout());
		barPanel.setPreferredSize(new Dimension(100, 20));
		progressBar.setMinimum(0);
		progressBar.setMaximum(areaAvailable / 2);
		progressBar.setOpaque(true);
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.blue);
		progressBar.setForeground(Color.GRAY);
		progressBar.setVisible(true);
		barPanel.add(createLevels());
		barPanel.add(progressBar);
		this.add(barPanel, BorderLayout.NORTH);
		updateBar();
	}
	private Component createLevels() {
		levelsField = new JTextField();
		levelsField.setText("" + level);
		return levelsField;
	}
	private void updateLevels() {
		levelsField.setText("" + level);
	}
	public void updateBar() {
		if (progressBar.getString().equals("100%")) {
			areaAvailable = startingAreaAvailable / 100 * (50 + 5 * (level - 1));
			progressBar.setMaximum(areaAvailable);
			System.out.print(areaAvailable);
			areaCutOff = 0;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gm = new SplitGameMap(dimensions);
			level++;
		}
		if (gm.getPolygon() != null) {
			areaCutOff = areaAvailable - gm.getPolygon().getHeight() * gm.getPolygon().getWidth();
		}
		progressBar.setValue(areaCutOff);
		if (level % 3 == 0) {
			gm = new SplitGameMap(dimensions);
		}
	}
	private void beginGame() {
		this.setPreferredSize(new Dimension(dimensions.getX(), dimensions.getY()));
		createMap();
		createDividerButtons();
		startTicks();
	}
	private void createDividerButtons() {
		openImages();

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		horizontalDividerButton = new JButton();
		verticalDividerButton = new JButton();

		horizontalDividerButton.setIcon(new ImageIcon(horizontal));
		verticalDividerButton.setIcon(new ImageIcon(vertical));

		horizontalDividerButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {
				typeOfDivider = "horizontal";
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});

		verticalDividerButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {
				typeOfDivider = "vertical";
			}
			@Override
			public void mouseReleased(MouseEvent e) {}
		});

		buttonPanel.add(horizontalDividerButton);
		buttonPanel.add(verticalDividerButton);
		buttonPanel.setBackground(Color.WHITE);

		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	private void openImages() {
		try {
			horizontal = ImageIO.read(new File("src/hoirzontal.png"));
		} catch (IOException e) {
			System.out.println("Unable to instantiate horiozntal");
		}
		try {
			vertical = ImageIO.read(new File("src/vertical.png"));
		} catch (IOException e) {
			System.out.println("Unable to instantiate vertical");
		}
		try {
			gameOverImage = ImageIO.read(new File("src/THEBACKGROUND.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			playAgainButton = ImageIO.read(new File("src/playAgain.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void startTicks() {
		t = new Timer(50, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				gm.tick();
				repaint();
				Toolkit.getDefaultToolkit().sync();
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
		if (!gm.getGameOver()) {
			updateLevels();
			updateBar();
			g.drawImage(img, 0, 0, dimensions.getX(), dimensions.getY(), null);
			gm.draw(g);
		}
		else {
			buttonPanel.remove(horizontalDividerButton);
			buttonPanel.remove(verticalDividerButton);
			//this.removeAll();
			g.drawImage(gameOverImage, 0, 0, dimensions.getX(), dimensions.getY(), null);
			iAmDead = new JPanel();
			iAmDead.setLayout(new FlowLayout());
			
			JButton playAgain = new JButton();
			playAgain.setIcon(new ImageIcon(playAgainButton));

			playAgain.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent arg0) {}
				@Override
				public void mouseEntered(MouseEvent arg0) {}
				@Override
				public void mouseExited(MouseEvent arg0) {}
				@Override
				public void mousePressed(MouseEvent arg0) {
				}
				@Override
				public void mouseReleased(MouseEvent arg0) {}
			});
			buttonPanel.setBackground(Color.WHITE);
			iAmDead.add(playAgain);
			this.add(playAgain, BorderLayout.SOUTH);
			//g.drawImage(gameOverImage, 0, 0, null);	
		}
	}
}
