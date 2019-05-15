import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.border.LineBorder;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
	
public class View extends JFrame{
	
	private BufferedImage background;
	public int backx;
	public int backspeed;	//match to sprite scroll speed
	private BufferedImage minimap;
	public int mapblipx;
	public int mapblipy;
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private Player bird;
	private QuizQ quiz;
	private BufferedImage birdImg;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ArrayList<BufferedImage> imgs = new ArrayList<>();
	//State state;
	
	JFrame frame =  new JFrame("Bird Game");
	JButton start = new JButton("Start");
	static DrawPanel drawPanel;
	MenuPanel menuPanel;
	JProgressBar energyBar;
	JProgressBar nestBar;
	
	public View() {
		background = createImage("Images/GameBackground.jpg");
		birdImg = createImage("Images/osprey2d_img.png");
	   	birdImg = resize(birdImg, 200, 200);
	   	BufferedImage foodImg = createImage("Images/food_bfish.png");
	   	foodImg = resize(foodImg, 100, 100);
	   	BufferedImage obstacleImg= createImage("Images/branchesd-obs.png");
	   	obstacleImg = resize(obstacleImg, 100, 100);
	   	BufferedImage nestpieceImg = createImage("Images/crd_nestpiece.png");
	   	nestpieceImg = resize(nestpieceImg, 100, 100);
		minimap = createImage("Images/mini.jpg");
	   	
		imgs.add(foodImg);
		imgs.add(obstacleImg);
		imgs.add(nestpieceImg);
		bird = new Player(screenSize.width / 10, screenSize.height / 2, birdImg);
		
		menuPanel = new MenuPanel();
		drawPanel = new DrawPanel();
		menuPanel.add(start);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.add(menuPanel);
		frame.pack();
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	public class DrawPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, backx, 0, screenSize.width, screenSize.height,  this);
			g.drawImage(background, backx+screenSize.width, 0, screenSize.width, screenSize.height,  this);
			if(backx <= 0 - screenSize.width)
				backx = 0;
			g.drawImage(bird.Image, (int)bird.xloc, (int)bird.yloc, birdImg.getWidth(), birdImg.getHeight(),  this);
			for(Sprite s: sprites) {
				g.drawImage(s.Image, (int)s.xloc, (int)s.yloc, s.getImgWidth(), s.getImgHeight(),  this);
			}
			g.drawImage(minimap, screenSize.width -260, screenSize.height - 375, 260, 314, this);
			g.setColor(Color.RED);
			g.fillOval(mapblipx, mapblipy, 15, 15);
		}
	}
	
	@SuppressWarnings("serial")
	public class MenuPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}
	}
   
    public void update(ArrayList<Sprite> s, Player b) {
		sprites = s;
		bird = b;

		energyBar.setValue(bird.energyLevel);
		nestBar.setValue(bird.nestProgress);
		
		if(backx == -backspeed){
			mapblipy -=10;
			mapblipx += 3;
		}
		backx -= backspeed;
		
		

		drawPanel.repaint();
		
		if (b.isDead()) {
			displayQuiz();
			b.resetDeath();
		}
	}
	
   public void displayQuiz() {
	   
	   QuizQ q = new QuizQ();
	   Answers[] options = q.getArrayAns();
	   JOptionPane.showOptionDialog(null, q.getQuestion(), "Come back to life if you answer correctly!",
			   JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			   null, q.getArrayAns(), options[q.getCorrectNum()]);
   }
    
   
	private BufferedImage createImage(String img) {
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(img));
    		return bufferedImage;
    	} 
		catch (IOException e) {
	    	e.printStackTrace();
		}
	   	return null;
	}
	
	public static void main(String[] args) {
		Controller a = new Controller();
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public Player getPlayer() {
		return bird;
	}
	
	private static BufferedImage resize(BufferedImage img, int height, int width) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}

	public Dimension getScreenSize() {
		return screenSize;
	}
	
	public void removeMenu() {
		backx = 0;
		mapblipx = screenSize.width - 130;
		mapblipy = screenSize.height - 60;
		backspeed = 6;
		energyBar = new JProgressBar(0, 100);
		nestBar = new JProgressBar(0, 100);
		energyBar.setValue(bird.energyLevel);
		nestBar.setValue(0);
      	energyBar.setStringPainted(true); 
        nestBar.setStringPainted(true);
		frame.remove(menuPanel);
		drawPanel.add(energyBar);
		drawPanel.add(nestBar);
		frame.add(drawPanel);
		drawPanel.requestFocusInWindow();
		drawPanel.requestFocus();
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}

	public ArrayList<BufferedImage> getImgs() {
		return imgs;
	}
}