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
	int piecesize = 100;
	int playersize = 200;
	public boolean tutorial = true;
	int step = 4;
	
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private Player bird;
	private QuizQ quiz;
	private BufferedImage ospreyImg;
	private BufferedImage clapperImg;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ArrayList<BufferedImage> imgs = new ArrayList<>();
	private ArrayList<BufferedImage> imgO = new ArrayList<>();
	private ArrayList<BufferedImage> imgC = new ArrayList<>();

	
	JFrame frame =  new JFrame("Bird Game");
	JButton startOsprey;
	JButton startClapper;

	static DrawPanel drawPanel;
	MenuPanel menuPanel;
	JProgressBar energyBar;
	JProgressBar nestBar;
	
	
	
	
	
	public View() {
		ImageIcon ospreyIcon = new ImageIcon("Images/ospreyIcon.png");
		ImageIcon clapperrailIcon = new ImageIcon("Images/clapperrailIcon.png");
		
		startOsprey = new JButton("Play as an Osprey", ospreyIcon);
		startClapper = new JButton("Play as a Clapper Rail", clapperrailIcon);
		
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
		nestImg = resize(nestImg, 400, 400);
	   	
		startClapper.setPreferredSize(new Dimension(500, 500));
		startOsprey.setPreferredSize(new Dimension(500, 500));
		imgO.add(ospreyFoodImg);
		imgO.add(ospreyObstacleImg);
		imgO.add(nestpieceImg);
		imgO.add(ospreyBackground);
		imgO.add(ospreyMinimap);
		
		imgC.add(clapperFoodImg);
		imgC.add(clapperObstacleImg);
		imgC.add(nestpieceImg);
		imgC.add(clapperBackground);
		imgC.add(clapperMinimap);
		imgs = imgO;
		
		bird = new Player(screenSize.width / 10, screenSize.height / 2, ospreyImg);
		nest = new Nest(1.1*screenSize.width, screenSize.height/2, nestImg);
		
		menuPanel = new MenuPanel();
		menuPanel.setBackground(Color.BLUE);

		startClapper.setActionCommand("Clapper Rail");
		startOsprey.setActionCommand("Osprey");

		menuPanel.add(startOsprey);
		menuPanel.add(startClapper);
		
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
			g.drawImage(imgs.get(3), backx, 0, screenSize.width, screenSize.height,  this);
			g.drawImage(imgs.get(3), backx+screenSize.width, 0, screenSize.width, screenSize.height,  this);
			if(backx <= 0 - screenSize.width)
				backx = 0;
			g.drawImage(bird.Image, (int)bird.xloc, (int)bird.yloc, bird.Image.getWidth(), bird.Image.getHeight(),  this);
			g.drawImage(imgs.get(4), 0, screenSize.height - 375, 260, 314, this);
			g.setColor(Color.RED);
			g.fillOval(mapblipx, mapblipy, 15, 15);
			if(tutorial) 
				tutorial(g);
			else {
				step = 4;//reset tutorial steps
				for(Sprite s: sprites) {
					g.drawImage(s.Image, (int)s.xloc, (int)s.yloc, s.getImgWidth(), s.getImgHeight(),  this);
				}
				
			}
			
		}
	}
	public void tutorial(Graphics g) {
			g.setFont(new Font("Courier", Font.BOLD,35));
			if(bird.migratory)
				g.drawString("This is You, a Mighty OSPREY, you're on a long journey home", 350, (int)screenSize.getHeight() *4/5);
			else
				g.drawString("This is You, a CLAPPER RAIL, living your life, minding your business", 350, (int)screenSize.getHeight() *4/5);
			
			try {
				Thread.sleep(2000);//increase/decrease "speed"
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		if( step<=4) {
				g.drawString("Eat Food", (int)screenSize.getWidth() /2 - 40, (int)screenSize.getHeight()/2 - 40);
				Food x = new Food( screenSize.getWidth() /2, screenSize.getHeight()/2, imgs.get(0));
				g.drawImage(x.Image, (int)x.xloc, (int)x.yloc, x.getImgWidth(), x.getImgHeight(),  this);
				
		} if(step <=3) {
				g.drawString("Collect Nest Pieces", (int)screenSize.getWidth() * 2/3 - 40, (int)screenSize.getHeight() /4 - 40);
				NestPiece y = new NestPiece(screenSize.getWidth() * 2/3, screenSize.getHeight() /4, imgs.get(2));
				g.drawImage(y.Image, (int)y.xloc, (int)y.yloc, y.getImgWidth(), y.getImgHeight(),  this);
				
		} if (step <= 2) {
				g.drawString("Avoid Dangers", (int)screenSize.getWidth() * 1/4, (int)screenSize.getHeight() *1/4);
				Obstacle z = new Obstacle(screenSize.getWidth() * 1/4, screenSize.getHeight() *1/4, imgs.get(1));
				g.drawImage(z.Image, (int)z.xloc, (int)z.yloc, z.getImgWidth(), z.getImgHeight(),  this);
				
		} if (step == 1) {g.drawString("Simply fly UP and DOWN with the arrow keys", (int)screenSize.getWidth() /2 -200, (int)screenSize.getHeight() *3/5);
					g.drawString("Press Space to begin", (int)screenSize.getWidth() /2 - 100, (int)screenSize.getHeight() *3/5 + 50);
					step++;
		} if (step == 0) {tutorial =false;}
		step--;

		
		repaint();
			
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
			
			drawPanel.repaint();
			
			
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
	    	removeGamePanel();
	    } else {
	    	JOptionPane.showMessageDialog(null, "Correct! Live Again!");
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
	
	//Main method, simply creates a controller object, runtime begins in that constructor.
	public static void main(String[] args) {
		Controller a = new Controller();
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
		backspeed = bird.energyLevel / 4;
		
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
			imgs = imgC;
			bird.Image = clapperImg;
		}
		else {
			imgs = imgO;
			bird.Image = ospreyImg;
		}
		drawPanel = new DrawPanel();
		drawPanel.add(energyBar);
		drawPanel.add(nestBar);
		frame.add(drawPanel);
		drawPanel.requestFocusInWindow();
		drawPanel.requestFocus();
		frame.invalidate();
		frame.validate();
		frame.repaint();
	}

	public void removeGamePanel() {
		frame.remove(drawPanel);
		tutorial = true;
		gameOver = false;
		bird = new Player(screenSize.width / 10, screenSize.height / 2, ospreyImg);
		sprites.clear();
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
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}

	public Player getPlayer() {
		return bird;
	}
}