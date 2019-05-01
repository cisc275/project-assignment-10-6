//package birdyRun;
import java.awt.image.BufferedImage;


public class Food extends Sprite{		//food class for food objects
	
	Food(int x, int y, BufferedImage img) {
		xloc = x;
		yloc = y;
		Image = img;
		type = "Food";
	}
	
}