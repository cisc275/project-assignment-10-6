import java.util.*;

import javax.swing.JTextArea;

import java.awt.image.BufferedImage;

public class Player extends Sprite{
	public int nestProgress = 0; //How far they have built the nest (0-100%)
	public int energyLevel = 50; //0-100%
	public BufferedImage clapperImage;
	public boolean migratory;
	public boolean dead;
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
	
	public void revive() {
		energyLevel = 50;
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
	
	public int getClapperImgWidth() {
		return clapperImage.getWidth();
	}

	public int getClapperImgHeight() {
		return clapperImage.getHeight();
	}
	
	public void fatigue() {
		energyLevel -= 1;
	}
	
	public boolean isDead() {
		if (energyLevel == 0) {
			dead = true;
		}
		else {
			dead = false;
		}
		return dead;
	}
	
	public void resetDeath () {
		dead = false;
		energyLevel = 50;
	}
	
	public boolean getMigratory() {
		return this.migratory;
	}
	
	public void setMigratory(boolean migratory) {
		this.migratory = migratory;
	}
	
	public void setDeath(boolean died) {
		if (died == true) {
			energyLevel = 0;
			dead = true;
		}
	}
}