//package birdyRun;
import java.awt.image.BufferedImage;


public class NestPiece extends Sprite { // nest pieces can be collected

	NestPiece(int x, Lane l, BufferedImage img) {
		xloc = x;
		lane = l;
		Image = img;
		type = "NestPiece";
	}	
}
