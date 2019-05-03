import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.JFrame.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Image;

public class View extends JFrame{
	private BufferedImage background;
	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private BufferedImage birdImg;
	private BufferedImage foodImg;
	private BufferedImage nestpieceImg;
	private BufferedImage obstacleImg;
	private Player bird;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	static JFrame frame =  new JFrame("Bird Game");
	DrawPanel drawPanel;
	
    public View() {
    	
    	// public BufferedImage resize(BufferedImage img, int newW, int newH) { 
    	
    	
    	background = createImage("src/Images/GameBackground.jpg");
    	
    	birdImg = createImage("src/Images/clapper_raild.png");
    	birdImg = resize(birdImg, 200, 200);
    	foodImg = createImage("src/Images/food_bfish.png");
    	foodImg = resize(foodImg, 100, 100);
    	obstacleImg= createImage("src/Images/branchesd-obs.png");
    	obstacleImg = resize(obstacleImg, 100, 100);
    	nestpieceImg = createImage("src/Images/crd_nestpiece.png");
    	nestpieceImg = resize(nestpieceImg, 100, 100);
		bird = new Player(0, screenSize.height / 2, birdImg);
		for (int i = 0; i < 10; i++) { 
            sprites.add(new NestPiece(i*2000, i, nestpieceImg)); 
        } 
		for (int i = 0; i < 50; i++) { 
            sprites.add(new Food(i*500, i, foodImg)); 
        } 
		for (int i = 0; i < 100; i++) { 
            sprites.add(new Obstacle(i*1000, i, obstacleImg)); 
        }
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		drawPanel = new DrawPanel();
		frame.add(drawPanel);
		frame.pack();
		frame.setVisible(true);
    }	
	
    private static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
    @SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background, 0, 0, screenSize.width, screenSize.height,  this);
			g.drawImage(bird.Image, bird.xloc, bird.yloc, bird.getImgWidth(), bird.getImgHeight(),  this);
			for(Sprite s: sprites) {
				g.drawImage(s.Image, s.xloc, s.yloc, s.getImgWidth(), s.getImgHeight(),  this);
			}
		}
	}
	
	public void update(ArrayList<Sprite> s, Player b) {
		sprites = s;
		bird = b;
		try {
			Thread.sleep(5);//increase/decrease "speed"
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
		drawPanel.repaint();
	}
	
	private BufferedImage createImage(String img) {
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(img));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
	}
	
	public static void main(String[] args) {
		Controller a = new Controller();
		frame.addKeyListener(a);
		a.start();
	}

	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	
	public Player getPlayer() {
		return bird;
	}
}