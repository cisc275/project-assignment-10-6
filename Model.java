import java.io.IOException;
import java.util.Scanner;

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
	Level level;
	int refRate = 200;
	
	Model () {
	
	}
	
	Model(int pic, int frame) {
		picSize = pic;
		frameStartSize = frame;
		level = new Level();
	}
	public static void main(String[] args) {
		Model x = new Model(0,0);
		//Scanner in = new Scanner(System.in);
		for(int i=0; i < (x.level.length-20);i++) {
			try{
				if(System.in.available()>0) {
					char c = (char)System.in.read();
					if(c == 'w' || c == 'u')
						x.level.p.changeLane("w");
					else if(c == 's' || c == 'd')
						x.level.p.changeLane("s");
					else if(!x.level.p.alive && c == 'y') {
						x.level.reborn();
					}
					else if(!x.level.p.alive && c == 'n') {
						x.level.p.setAlive(true);//1 last frame;
						System.out.println(x.level);
						return;
					}
					else {}
				}
			}catch(IOException e){
				
			}
			System.out.println(x.level);
			x.level.update();
			try {
				Thread.sleep(200);//increase/decrease "speed"
	    	} catch (InterruptedException e) {
	    		e.printStackTrace();
	    	}
	}
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
	public String toString() {
		return level.toString();
	}
	public int getEnergyLevel() {
		return this.energyLevel;
	}
	public void setEnergyLevel(int energy) {
		this.energyLevel = energy;
	}
	
	public int getNestProgress() {
		return this.nestProgress;
	}
	
	public void setNestProgress(int prog) {
		this.nestProgress = prog;
	}
}
