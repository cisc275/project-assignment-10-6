//package birdyRun;


public class Model {
	int picSize;
	int frameStartSize;
	int xloc;
	int yloc;
	int xIncr = 8;
    int yIncr = 2;
	int nestProgress = 0; //How far they have built the nest (0-100%)
	int energyLevel = 50; //0-100%
	int levelProgress = 0;
	
	Model(int pic, int frame) {
		picSize = pic;
		frameStartSize = frame;
	}
	
	public int detectCollision() {
		return 0;
	}
	public void move() {
		/*if() {
			yloc += 1;
		else if () {
			yloc -= 1;
		}	*/		
	}
	
	public void updateLocationAndDirection() {
		xloc += xIncr;
		yloc += yIncr;


	}
	
	public void regen() {
		energyLevel += 2;
	}
	
	public void fatigue() {
		energyLevel -= 1;
	}
	
	public void buildNest() {
		nestProgress += 10;
	}
	
	public void quiz() {
	
	}
	
	public int getX() {
		return xloc;
	}
	
	public int getY() {
		return yloc;
	}
}
