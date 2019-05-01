//package birdyRun;
import java.awt.image.BufferedImage;

public class Obstacle extends Sprite {
	
	Obstacle(int x, int y, BufferedImage img) {
		xloc = x;
		yloc = y;
		Image = img;
		type = "Obsacle";
	}
	
}
