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
import java.net.URL;

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
    Image img;
	Image horizontal, vertical;
    private int level;
    private JProgressBar progressBar;
    private int areaCutOff;
    private String typeOfDivider = null;
    JTextField levelsField;
    private final int startingAreaAvailable;
    private int levelAreaAvailable;
    private int currentAreaAvailable;
    private Image gameOverImage;
    private Image nextLevelImage;
    private boolean nextLevelImageShouldShow = false;
    private int count = 0;
 
    public SplitPanel(int width, int length) {
        this.setLayout(new BorderLayout());
        dimensions = new Vector(width, length);
        openBackgroundImg();
        Color backgroundColor = Color.BLUE;
        this.setBackground(backgroundColor);
 
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
            }
 
            @Override
            public void mouseEntered(MouseEvent arg0) {
            }
 
            @Override
            public void mouseExited(MouseEvent arg0) {
            }
 
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
            public void mouseReleased(MouseEvent arg0) {
            }
        });
        level = 1;
        beginGame();
        startingAreaAvailable = gm.getPolygon().getHeight() * gm.getPolygon().getWidth();
        progressBar = new JProgressBar();
        setUpProgressBar(width, length);
        areaCutOff = 0;
    }
 
    private void openBackgroundImg() {
    	URL imageUrl = this.getClass().getResource("/background.jpg");
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage(imageUrl);
    }
 
    private void setUpProgressBar(int width, int length) {
        JPanel barPanel = new JPanel();
        barPanel.setLayout(new GridLayout());
        barPanel.setPreferredSize(new Dimension(100, 20));
        progressBar.setMinimum(0);
        System.out.println(startingAreaAvailable);
        progressBar.setMaximum(startingAreaAvailable / 2);
        progressBar.setOpaque(true);
        progressBar.setStringPainted(true);
        progressBar.setBackground(Color.blue);
        progressBar.setForeground(Color.GRAY);
        progressBar.setVisible(true);
        barPanel.add(createLevels());
        barPanel.add(progressBar);
        this.add(barPanel, BorderLayout.NORTH);
    }
 
    private Component createLevels() {
        levelsField = new JTextField();
        updateLevels();
        return levelsField;
    }
 
    private void updateLevels() {
        levelsField.setText("Level: " + level);
    }
 
    public void updateBar(Graphics g) {
        System.out.println("levelArea " + levelAreaAvailable);
        System.out.println("L " + level);
        if (progressBar.getString().equals("100%")) {
            nextLevelImageShouldShow = true;
            count = 30;
            areaCutOff = 0;
            levelAreaAvailable = startingAreaAvailable / 100 * (50 + 10 * (level - 1));
            progressBar.setMaximum(levelAreaAvailable);
            gm = new SplitGameMap(dimensions);
           // gm.createBall();
            level++;
        }
        currentAreaAvailable = gm.getPolygon().getWidth() * gm.getPolygon().getHeight();
        System.out.println("current " + currentAreaAvailable);
        areaCutOff = startingAreaAvailable - currentAreaAvailable;
        System.out.println("cutOFf " + areaCutOff);
        progressBar.setValue(areaCutOff);
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
            }
 
            @Override
            public void mouseEntered(MouseEvent arg0) {
            }
 
            @Override
            public void mouseExited(MouseEvent arg0) {
            }
 
            @Override
            public void mousePressed(MouseEvent arg0) {
                typeOfDivider = "horizontal";
            }
 
            @Override
            public void mouseReleased(MouseEvent arg0) {
            }
        });
 
        verticalDividerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
 
            @Override
            public void mouseEntered(MouseEvent e) {
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
            }
 
            @Override
            public void mousePressed(MouseEvent e) {
                typeOfDivider = "vertical";
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
            }
        });
 
        buttonPanel.add(horizontalDividerButton);
        buttonPanel.add(verticalDividerButton);
        buttonPanel.setBackground(Color.CYAN);
 
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
 
    private void openImages() {
    	URL horizontalUrl = this.getClass().getResource("/hoirzontal.png");
		Toolkit tk = Toolkit.getDefaultToolkit();
		horizontal = tk.getImage(horizontalUrl);
		
		URL verticalUrl = this.getClass().getResource("/vertical.png");
		tk = Toolkit.getDefaultToolkit();
		vertical = tk.getImage(verticalUrl);
		
		URL gameOverUrl = this.getClass().getResource("/THEBACKGROUND.jpg");
		tk = Toolkit.getDefaultToolkit();
		gameOverImage = tk.getImage(gameOverUrl);
		
		URL nextLevelUrl = this.getClass().getResource("/nextLevelImage.jpeg");
		tk = Toolkit.getDefaultToolkit();
		nextLevelImage = tk.getImage(nextLevelUrl);

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
        if (nextLevelImageShouldShow) {
            g.drawImage(nextLevelImage, 0, 0, dimensions.getX(), dimensions.getY(),  null);
            count--;
            if (count <= 0) {
                nextLevelImageShouldShow = false;
            }
        }
        else {
            if (!gm.getGameOver()) {
                updateLevels();
                updateBar(g);
                g.drawImage(img, 0, 0, dimensions.getX(), dimensions.getY(), null);
                gm.draw(g);
            } else {
                this.removeAll();
                g.drawImage(gameOverImage, 0, 0, dimensions.getX(), dimensions.getY(), null);
            }
        }
    }
}
