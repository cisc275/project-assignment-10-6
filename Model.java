import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
//package birdyRun;


public class Model {
	private int nestProgress = 0; //How far they have built the nest (0-100%)
	private int energyLevel = 50; //0-100%
	private int levelProgress = 0;
	private ArrayList<Sprite> sprites;
	private Player bird;
	
	public Model(ArrayList<Sprite> o, Player b) {
		sprites = o;
		bird = b;
	}

	public void detectCollision() {
		Iterator<Sprite> itr = sprites.iterator();
		while (itr.hasNext()) {
			Sprite I = itr.next();
		
	
		}
	}
	
	/*public Area getOutline(Color target, BufferedImage bi) {
		// construct the GeneralPath
		GeneralPath gp = new GeneralPath();

		boolean cont = false;
		int targetRGB = target.getRGB();
		for (int xx=0; xx<bi.getWidth(); xx++) {
			for (int yy=0; yy<bi.getHeight(); yy++) {
				if (bi.getRGB(xx,yy)==targetRGB) {
					if (cont) {
						gp.lineTo(xx,yy);
						gp.lineTo(xx,yy+1);
						gp.lineTo(xx+1,yy+1);
						gp.lineTo(xx+1,yy);
						gp.lineTo(xx,yy);
					} 	
					else if (!cont) {
						gp.moveTo(xx,yy);
					}
					cont = true;
				} 
				else {
					cont = false;
				}
			}
			cont = false;
		}
		gp.closePath();

		// construct the Area from the GP & return it
		return new Area(gp);
	}*/
	
	public void move(String x) {
		if(x.equals("up")) {
			bird.yloc = bird.yloc - 2;
		}
		else if (x.equals("down")) {
			bird.yloc = bird.yloc + 2;
		}		
	}
	
	
	public void updateLocation() {
		for(Sprite s: sprites) {
			s.xloc = s.xloc - 8;
		}
	}
	
	public void regen() {
		energyLevel += 2;
	}
	
	public void fatigue() {
		energyLevel -= 1;
	}
	
	public void buildNest() {
		nestProgress += 10;
	}
	
	public void damage() {
		energyLevel -= 30;
	}
	
	public ArrayList<Sprite> getSprites() {
		return sprites;
	}
	
	public Player getPlayer() {
		return bird;
	}
}
