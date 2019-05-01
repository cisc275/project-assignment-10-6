//package birdyRun;
import java.util.*;
import java.awt.image.BufferedImage;

public class Player extends Sprite{
	
	Player(int x, Lane l, BufferedImage img) {
		xloc = x;
		lane = l;
		Image = img;
		type = "Player";
	}
}
