import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.util.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
//package birdyRun;


public class Model implements Serializable{
	private int levelProgress = 0;
	private ArrayList<Sprite> sprites = new ArrayList<>();
	private Player bird;
	private boolean birdDead = false;
	private Dimension screenSize;
	private ArrayList<BufferedImage> imgs = new ArrayList<>();
	private double yIncr = 0.0;
	private Nest nest;
	private boolean gameOver;

	private double lane1 = 0.0;
	private double lane2;
	private double lane3;
	private double lane4;
	private double target;
	private boolean moving = false;
	
	private int lock;


	public Model(Dimension bounds, Player b, Nest n, ArrayList<BufferedImage> a) {
		nest = n;
		screenSize = bounds;
		bird = b;
		imgs = a;

		lane2 = (double)(screenSize.height / 4);
		lane3 = (double)(screenSize.height / 2);
		lane4 = (double)(screenSize.height*3 / 4);
	}

	public void detectCollision() {
		Iterator<Sprite> itr = sprites.iterator();
		while (itr.hasNext()) {
			Sprite s = itr.next();
			Rectangle p;
			Rectangle o;
			p = new Rectangle((int)bird.xloc,(int)bird.yloc,bird.getImgWidth(),bird.getImgHeight());

			if (s.type.equals("Nest")) {
				o = new Rectangle((int)nest.xloc, 0, nest.getImgWidth(), screenSize.height);
				if(o.intersects(p)) {
					gameOver = true;
					levelProgress= 0;
					lock =0;
					bird.energyLevel = 40;
				}
			}
			else {
				o = new Rectangle((int)s.xloc,(int)s.yloc,s.getImgWidth(),s.getImgHeight());
				if (o.intersects(p)) {
					
						if (imageCollision(s.xloc, s.yloc, s.Image, bird.xloc, bird.yloc, bird.Image)) {
							if (s.type.equals("Food")) {
								bird.regen();
								itr.remove();
							}
							else if (s.type.equals("NestPiece")) {
								bird.buildNest();
								itr.remove();
							}
							else if (s.type.equals("Obstacle")) {
								bird.damage();
								itr.remove();
							}
						}
					
						
					}
				}
			
		}
	}
	
	//Collision detection of images taking 2 objects' x's and y's and images. comparing for collision detection, updating "destruction"
	public static boolean imageCollision(double x1, double y1, BufferedImage image1, double x2, double y2, BufferedImage image2) {
		// initialization
		double width1 = x1 + image1.getWidth() -1;
        double height1 = y1 + image1.getHeight() -1;
        double width2 = x2 + image2.getWidth() -1;
        double height2 = y2 + image2.getHeight() -1;

		int xstart = (int) Math.max(x1, x2);
		int ystart = (int) Math.max(y1, y2);
		int xend   = (int) Math.min(width1, width2);
		int yend   = (int) Math.min(height1, height2);

		// intersection rect
		int toty = Math.abs(yend - ystart);
		int totx = Math.abs(xend - xstart);

		for (int y=1;y < toty-1;y++){
			int ny = Math.abs(ystart - (int) y1) + y;
			int ny1 = Math.abs(ystart - (int) y2) + y;
			for (int x=1;x < totx-1;x++) {
				int nx = Math.abs(xstart - (int) x1) + x;
				int nx1 = Math.abs(xstart - (int) x2) + x;
				try {
					if (((image1.getRGB(nx,ny) & 0xFF000000) != 0x00) && ((image2.getRGB(nx1,ny1) & 0xFF000000) != 0x00)) {
					// collide!!
					return true;
					}
				} 
				catch (Exception e) {
					System.out.println("s1 = "+nx+","+ny+"  -  s2 = "+nx1+","+ny1);
				}
			}
		}
		return false;
	}
	
	public void move(String x) {
		if(!moving){
			if(x.equals("up")) {
				if(bird.yloc <= lane1)
					yIncr = 0.0;
				else{
					yIncr = 0- (lane2 / 16); 
					moving = true;
					target = bird.yloc - lane2; //minus 1/4 the screen
				}	
			}
			else if (x.equals("down")) {
				if(bird.yloc >= lane4)
					yIncr = 0.0;
				else{
					yIncr = lane2 / 16;
					moving = true;
					target = bird.yloc +lane2; // plus 1/4 the screen
				}
			}		
			else if (x.equals("stop")) {
				yIncr = 0.0;
				moving = false;
			}
		}
	}
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public void spawnObjects() {
		if(!bird.isDead()) {
			for(int i = 0; i < 10; i++) {
				sprites.add(new Food(randX(), randY(), imgs.get(0))); 
			}
			
			for(int i = 0; i < 7; i++) {
				sprites.add(new Obstacle(randX(), randY(), imgs.get(1))); 
			}
			for(int i = 0; i < 2; i++) {
				sprites.add(new NestPiece(randX(), randY(), imgs.get(2))); 
			}
		}
	}
	
	public double randY() { 
		List<Double> yValue = new ArrayList<>(); 
		yValue.add(lane1);
		yValue.add(lane2);
		yValue.add(lane3);
		yValue.add(lane4);
        Random rand = new Random(); 
        return yValue.get(rand.nextInt(yValue.size())); 
    } 
	
	public double randX() {
        return (Math.random() *((screenSize.width * 2) + 1)) + (1.1 * screenSize.width); 
	}

	public void updateLocation() {
		if (!gameOver) {
			if(moving){
				bird.yloc += yIncr;
				if(bird.yloc == target){
					yIncr = 0.0;
					moving = false;			
				}
			} 
			if (levelProgress <= 5) {
				lock = 0;
				if(!sprites.isEmpty()) {
					Iterator<Sprite> itr = sprites.iterator();
					while(itr.hasNext()) {
						Sprite s = itr.next();
						s.xloc = s.xloc - (.25 * bird.energyLevel); 
						if (s.xloc < -100) {
							itr.remove();
						}
					}
					detectCollision();
				}
				else {
					spawnObjects();
					levelProgress++;
					System.out.println("Level progress: " + levelProgress);
				}
			}
			else {
				if(lock == 0) {
					sprites.clear();
				}
				if (!sprites.isEmpty()) {
					nest.xloc = nest.xloc - 7;
					detectCollision();
				}
				else {
					nest.xloc = 1.1 * screenSize.width;
					sprites.add(nest);
					lock = 1;
				}
			}
			if (bird.isDead()) {
				QuizQ quiz = new QuizQ(bird.getMigratory());
				if (quiz.getSubmitted()) {
					birdDead = false;
					bird.resetDeath();
					
				} else {
					birdDead = true;
					bird.setDeath(true);
					sprites.clear();
					levelProgress = 0;
					lock = 0;
					bird.energyLevel = 40;
				}
			}
		}
	}
	public Nest getNest() {
		return nest;
	}
	public void setImgs(ArrayList<BufferedImage> i){
		this.imgs = i;
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	
	public boolean getGameOver() {
		return gameOver;
	}
	
	public void setGameOver(boolean g) {
		gameOver = g;
	}
	
	public void setLevelProgress(int n) {
		levelProgress = n;
	}
	
	public Player getPlayer() {
		return bird;
	}
}