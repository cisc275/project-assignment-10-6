import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
	
	
	public class View extends JFrame{
		
		
		private BufferedImage background;
		private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		private Player bird;
		private BufferedImage birdImg;
		//private BufferedImage foodImg;
		//private BufferedImage nestpieceImg;
		//private BufferedImage obstacleImg;
		private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		private ArrayList<BufferedImage> imgs = new ArrayList<>();
	
		static JFrame frame =  new JFrame("Bird Game");
		DrawPanel drawPanel;
	
	    public View() {
			background = createImage("Images/GameBackground.jpg");
			birdImg = createImage("src/Images/clapper_raild.png");
	    	birdImg = resize(birdImg, 200, 200);
	    	BufferedImage foodImg = createImage("src/Images/food_bfish.png");
	    	foodImg = resize(foodImg, 100, 100);
	    	BufferedImage obstacleImg= createImage("src/Images/branchesd-obs.png");
	    	obstacleImg = resize(obstacleImg, 100, 100);
	    	BufferedImage nestpieceImg = createImage("src/Images/crd_nestpiece.png");
	    	nestpieceImg = resize(nestpieceImg, 100, 100);
	    	
			imgs.add(foodImg);
			imgs.add(obstacleImg);
			imgs.add(nestpieceImg);
			bird = new Player(0, screenSize.height / 2, birdImg);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			drawPanel = new DrawPanel();
			frame.add(drawPanel);
			frame.pack();
			frame.setVisible(true);
	    }
	    
	    @SuppressWarnings("serial")
		private class DrawPanel extends JPanel {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(background, 0, 0, screenSize.width, screenSize.height,  this);
				g.drawImage(bird.Image, (int)bird.xloc, (int)bird.yloc, birdImg.getWidth(), birdImg.getHeight(),  this);
				for(Sprite s: sprites) {
					g.drawImage(s.Image, (int)s.xloc, (int)s.yloc, s.getImgWidth(), s.getImgHeight(),  this);
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
	
		public ArrayList<BufferedImage> getImgs() {
			return imgs;
		}
	}