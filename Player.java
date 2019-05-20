import java.util.*;

import javax.swing.JTextArea;

import java.awt.image.BufferedImage;

public class Player extends Sprite{
	public int nestProgress = 0; //How far they have built the nest (0-100%)
	public int energyLevel = 50; //0-100%
	public boolean migratory;
	public boolean dead;
	//public QuizQ quiz;
	public boolean iframe = false;
	
	Player(double x, double y, BufferedImage img) {
		xloc = x;
		yloc = y;
		Image = img;
		type = "Player";
	}
	
	public void regen() {
		if (energyLevel < 100) {
			energyLevel += 5;
		}
	}
	
	public void revive() {
		energyLevel = 40;
		iframe = false;
	}
	
	public void buildNest() {
		if (nestProgress < 100) {
			nestProgress += 10;
		}
	}
	
	public void damage() {
		if (energyLevel > 0) {
			energyLevel -= 20;
			if (energyLevel < 10) {
				energyLevel = 0;
			}
		}
	}
	
	public int getImgWidth() {
		return Image.getWidth();
	}

	public int getImgHeight() {
		return Image.getHeight();
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