//package birdyRun;
import java.util.*;
import java.awt.image.BufferedImage;

public class Player extends Sprite{
	
	Player(int x, int y, BufferedImage img) {
		xloc = x;
		yloc = y;
		Image = img;
		type = "Player";
	}
}
