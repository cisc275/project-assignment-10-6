//package birdyRun;
import java.awt.image.BufferedImage;


public class Food extends Sprite{		//food class for food objects
	
	Food(int x, Lane l, BufferedImage img) {
		xloc = x;
		lane = l;
		Image = img;
		type = "Food";
	}
	
}