import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import java.util.HashSet;
import java.util.Set;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.AWTKeyStroke;
import javax.swing.ImageIcon;

public class View extends JFrame{
	
	private BufferedImage ospreyBackground;
	private BufferedImage clapperBackground;
	private BufferedImage endGameBackground;
	public int backx;
	public int backspeed;	//match to sprite scroll speed
	private BufferedImage ospreyMinimap;
	private BufferedImage clapperMinimap;
	public int mapblipx;
	public int mapblipy;
	private Nest nest;
	private boolean gameOver;
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private Player bird;
	private QuizQ quiz;
	private BufferedImage ospreyImg;
	private BufferedImage clapperImg;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ArrayList<BufferedImage> imgs = new ArrayList<>();
	//private boolean migratory;
	
	JFrame frame =  new JFrame("Bird Game");
	JButton startOsprey;
	JButton startClapper;
	JButton startOspreyTutorial;
	JButton startClapperTutorial;
	static ospreyDrawPanel ospreydrawPanel;
	static clapperDrawPanel clapperdrawPanel;
	MenuPanel menuPanel;
	JProgressBar energyBar;
	JProgressBar nestBar;
	
	
	
	public View() {
		ImageIcon ospreyIcon = new ImageIcon("Images/ospreyIcon.png");
		ImageIcon clapperrailIcon = new ImageIcon("Images/clapperrailIcon.png");
		startOsprey = new JButton("Play as an Osprey", ospreyIcon);
		startClapper = new JButton("Play as a Clapper Rail", clapperrailIcon);
		startOspreyTutorial = new JButton("Start Osprey tutorial");
		startClapperTutorial = new JButton("Start Clapper Rail tutorial");
		ospreyBackground = createImage("Images/GameBackground.png");
		ospreyImg = createImage("Images/osprey2d_img.png");
	   	ospreyImg = resize(ospreyImg, 200, 200);
	   	BufferedImage ospreyFoodImg = createImage("Images/food_bfish.png");
	   	ospreyFoodImg = resize(ospreyFoodImg, 100, 100);
	   	BufferedImage ospreyObstacleImg= createImage("Images/branchesd-obs.png");
	   	ospreyObstacleImg = resize(ospreyObstacleImg, 100, 100);
		ospreyMinimap = createImage("Images/mini.jpg");
		
		clapperBackground = createImage("Images/Clapper_background.png");
		clapperImg = createImage("Images/clapper_rail.png");
	   	clapperImg = resize(clapperImg, 200, 200);
	   	BufferedImage clapperFoodImg = createImage("Images/food_bfish.png");
	   	clapperFoodImg = resize(clapperFoodImg, 100, 100);
	   	BufferedImage clapperObstacleImg= createImage("Images/branchesd-obs.png");
	   	clapperObstacleImg = resize(clapperObstacleImg, 100, 100);
		clapperMinimap = createImage("Images/mini.jpg");
		
		BufferedImage nestpieceImg = createImage("Images/crd_nestpiece.png");
	   	nestpieceImg = resize(nestpieceImg, 100, 100);
	   	
	   	BufferedImage nestImg = createImage("Images/nest.png");
		nestImg = resize(nestImg, 100, 100);
	   	
		startClapper.setPreferredSize(new Dimension(500, 500));
		startOsprey.setPreferredSize(new Dimension(500, 500));
		imgs.add(ospreyFoodImg);
		imgs.add(ospreyObstacleImg);
		imgs.add(nestpieceImg);
		bird = new Player(screenSize.width / 10, screenSize.height / 2, ospreyImg, clapperImg);
		nest = new Nest(1.1*screenSize.width, screenSize.height/2, nestImg);
		
		menuPanel = new MenuPanel();
		menuPanel.setBackground(Color.BLUE);
		startClapperTutorial.setActionCommand("Clapper Tutorial");
		startOspreyTutorial.setActionCommand("Osprey Tutorial");
		startClapper.setActionCommand("Clapper Rail");
		startOsprey.setActionCommand("Osprey");
		menuPanel.add(startClapperTutorial);
		menuPanel.add(startOspreyTutorial);
		menuPanel.add(startOsprey);
		menuPanel.add(startClapper);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.add(menuPanel);
		frame.pack();
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	public class ospreyDrawPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(ospreyBackground, backx, 0, screenSize.width, screenSize.height,  this);
			g.drawImage(ospreyBackground, backx+screenSize.width, 0, screenSize.width, screenSize.height,  this);
			if(backx <= 0 - screenSize.width)
				backx = 0;
			g.drawImage(bird.Image, (int)bird.xloc, (int)bird.yloc, ospreyImg.getWidth(), ospreyImg.getHeight(),  this);
			for(Sprite s: sprites) {
				g.drawImage(s.Image, (int)s.xloc, (int)s.yloc, s.getImgWidth(), s.getImgHeight(),  this);
			}
			g.drawImage(ospreyMinimap, screenSize.width -260, screenSize.height - 375, 260, 314, this);
			g.setColor(Color.RED);
			g.fillOval(mapblipx, mapblipy, 15, 15);
			
			
		}
	}
	
	@SuppressWarnings("serial")
	public class clapperDrawPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(clapperBackground, backx, 0, screenSize.width, screenSize.height,  this);
			g.drawImage(clapperBackground, backx+screenSize.width, 0, screenSize.width, screenSize.height,  this);
			if(backx <= 0 - screenSize.width)
				backx = 0;
			g.drawImage(bird.clapperImage, (int)bird.xloc, (int)bird.yloc, clapperImg.getWidth(), clapperImg.getHeight(),  this);
			for(Sprite s: sprites) {
				g.drawImage(s.Image, (int)s.xloc, (int)s.yloc, s.getImgWidth(), s.getImgHeight(),  this);
			}
			g.drawImage(clapperMinimap, screenSize.width -260, screenSize.height - 375, 260, 314, this);
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
   
    public void update(ArrayList<Sprite> s, Player b, boolean g) {
		gameOver = g;
		if (gameOver) {
			removeGamePanel();
		}
		else {
			sprites = s;
			bird = b;

			energyBar.setValue(bird.energyLevel);
			nestBar.setValue(bird.nestProgress);
			
			if(backx == -backspeed){
				mapblipy -=10;
				mapblipx += 3;
			}
			backx -= backspeed;
			
			
			if (!bird.getMigratory()) {
				clapperdrawPanel.repaint();
			}                         
			else {
				ospreydrawPanel.repaint();
			}
			
			if (bird.isDead()) {
				displayQuiz();
				//b.resetDeath();
			}
		}
	}
	
   public void displayQuiz() {

	   QuizQ q = new QuizQ(bird.getMigratory());
	   Answers[] options = q.getOptions();

	   
	   
	   //JOptionPane pane = new JOptionPane(q.getQuestion(), JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_CANCEL_OPTION);
		int option =  JOptionPane.showOptionDialog(null, q.getQuestion(), "Answer correctly to come back to life!",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		null, options, options[0]);
	   

	    if (option != q.getCorrectAns(options).getNum()) { // answer submitted is not correct
	    	JOptionPane.showMessageDialog(null, "Not Correct!");
	    	q.setSubmitted(false);
	    } else {
	    	JOptionPane.showMessageDialog(null, "Correct!");
	    	q.setSubmitted(true);
	    	bird.revive();
	    }
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
		
		// Energy and Nest Progress bars:
		
		
		energyBar = new JProgressBar(0, 100);
		nestBar = new JProgressBar(0, 100);
		energyBar.setValue(bird.energyLevel);
		nestBar.setValue(0);
      	energyBar.setStringPainted(true); 
        nestBar.setStringPainted(true);
        energyBar.setString(String.format("Energy: ", bird.energyLevel));
        nestBar.setString(String.format("Nest Progress: ", bird.nestProgress));
        
        
        
        
        
        
		frame.remove(menuPanel);
		if (!bird.getMigratory()) {
			clapperdrawPanel = new clapperDrawPanel();
			clapperdrawPanel.add(energyBar);
			clapperdrawPanel.add(nestBar);
			frame.add(clapperdrawPanel);
			clapperdrawPanel.requestFocusInWindow();
			clapperdrawPanel.requestFocus();
		}
		else {
			ospreydrawPanel = new ospreyDrawPanel();
			ospreydrawPanel.add(energyBar);
			ospreydrawPanel.add(nestBar);
			frame.add(ospreydrawPanel);
			ospreydrawPanel.requestFocusInWindow();
			ospreydrawPanel.requestFocus();
		}
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}
	
	public void removeGamePanel() {
		if (!bird.getMigratory()) {
			frame.remove(clapperdrawPanel);
		} 
		else {
			frame.remove(ospreydrawPanel);
		}
		gameOver = false;
		bird.energyLevel = 50;
		frame.add(menuPanel);
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}

	public ArrayList<BufferedImage> getImgs() {
		return imgs;
	}
	
	public void setGameOver(boolean g) {
		gameOver = g;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}

	public Nest getNest() {
		return nest;
	}
}