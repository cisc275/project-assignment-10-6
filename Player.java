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
	public final int maxHealth = 100;
	public final int healthIncrease = 5;
	public final int respawnHealth = 40;
	public final int maxNestProgress = 100;
	public final int nestBuildIncrease = 20;
	public final int minEnergyLevel = 0;
	public final int obstacleDamage = 20;
	
	Player(double x, double y, BufferedImage img) {
		xloc = x;
		yloc = y;
		Image = img;
		type = "Player";
	}
	
	//all the game mechanics for each of the spites(food = regen, nestpiece = build nest, obstacle = damage, correct quiz answer = revive)
	public void regen() {
		if (energyLevel < maxHealth) {
			energyLevel += healthIncrease;
		}
	}
	
	public void revive() {
		energyLevel = respawnHealth;
		iframe = false;
	}
	
	public void buildNest() {
		if (nestProgress < maxNestProgress) {
			nestProgress += nestBuildIncrease;
		}
	}
	
	public void damage() {
		if (energyLevel > minEnergyLevel) {
			energyLevel -= obstacleDamage;
			if (energyLevel < 10) {
				energyLevel = minEnergyLevel;
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