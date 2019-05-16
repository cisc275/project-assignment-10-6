import java.util.*;

import javax.swing.JTextArea;

import java.awt.image.BufferedImage;

public class Player extends Sprite{
	public int nestProgress = 0; //How far they have built the nest (0-100%)
	public int energyLevel = 50; //0-100%
	public BufferedImage clapperImage;
	//public QuizQ quiz;
	
	Player(double x, double y, BufferedImage img, BufferedImage img2) {
		xloc = x;
		yloc = y;
		Image = img;
		clapperImage = img2;
		type = "Player";
	}
	
	public void regen() {
		if (energyLevel < 100) {
			energyLevel += 2;
		}
	}
	
	public void buildNest() {
		if (nestProgress < 100) {
			nestProgress += 10;
		}
	}
	
	public void damage() {
		if (energyLevel > 0) {
			energyLevel -= 30;
			if (energyLevel < 0) {
				energyLevel = 0;
			}
		}
	}
	
	public void fatigue() {
		energyLevel -= 1;
	}
	
	public boolean isDead() {
		if (energyLevel == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void resetDeath () {
		energyLevel = 50;
	}
}