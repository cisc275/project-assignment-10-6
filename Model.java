import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
//package birdyRun;


public class Model {
	private int levelProgress = 0;
	private ArrayList<Sprite> sprites = new ArrayList<>();
	private Player bird;
	private boolean birdDead = false;
	private Dimension screenSize;
	private ArrayList<BufferedImage> imgs = new ArrayList<>();
	private double yIncr = 0.0;
	
	public Model(Dimension bounds, Player b, ArrayList<BufferedImage> a) {
		screenSize = bounds;
		bird = b;
		imgs = a;
	}

	public void detectCollision() {
		Iterator<Sprite> itr = sprites.iterator();
		while (itr.hasNext()) {
			Sprite s = itr.next();
			Rectangle o = new Rectangle((int)s.xloc,(int)s.yloc,s.getImgWidth(),s.getImgHeight());
			Rectangle p = new Rectangle((int)bird.xloc,(int)bird.yloc,bird.getImgWidth(),bird.getImgHeight());
			if (o.intersects(p)) {
				if (isPixelCollide(s.xloc, s.yloc, s.Image, bird.xloc, bird.yloc, bird.Image)) {
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
	public static boolean isPixelCollide(double x1, double y1, BufferedImage image1, double x2, double y2, BufferedImage image2) {
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
		if(x.equals("up")) {
			yIncr = -4.0;
		}
		else if (x.equals("down")) {
			yIncr = 4.0;
		}		
		else if (x.equals("stop")) {
			yIncr = 0.0;
		}
	}
	
	public boolean isBirdDead() {
		return false;
	}
	
	public void spawnObjects() {
		if(!birdDead) {
			for(int i = 0; i < 15; i++) {
				sprites.add(new Food(randX(), randY(), imgs.get(0))); 
			}
			for(int i = 0; i < 7; i++) {
				sprites.add(new Obstacle(randX(), randY(), imgs.get(1))); 
			}
			for(int i = 0; i < 5; i++) {
				sprites.add(new NestPiece(randX(), randY(), imgs.get(2))); 
			}
		}
	}
	
	public double randY() { 
		List<Double> yValue = new ArrayList<>(); 
		yValue.add((double)(screenSize.height / 4));
		yValue.add((double)((screenSize.height * 2) / 4));
		yValue.add((double)((screenSize.height * 3) / 4));
		yValue.add(0.0);
        Random rand = new Random(); 
        return yValue.get(rand.nextInt(yValue.size())); 
    } 
	
	public double randX() {
		return (Math.random() * (((screenSize.width * 2) - screenSize.width) + 1)) + screenSize.width;
	}
	
	public void updateLocation() {
		bird.yloc += yIncr;
		if(!sprites.isEmpty()) {
			Iterator<Sprite> itr = sprites.iterator();
			while(itr.hasNext()) {
				Sprite s = itr.next();
				s.xloc = s.xloc - 4;
				if (s.xloc < -100) {
					itr.remove();
				}
			}
			detectCollision();
		}
		else {
			spawnObjects();
		}
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	
	public Player getPlayer() {
		return bird;
	}
}