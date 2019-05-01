//package birdyRun;
import java.awt.image.BufferedImage;

public class Obstacle extends Sprite {
	
	Obstacle(int x, Lane l, BufferedImage img) {
		xloc = x;
		lane = l;
		Image = img;
		type = "Obsacle";
	}
	
}
