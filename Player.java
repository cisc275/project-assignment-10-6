//package birdyRun;
import java.util.*;
public class Player extends Sprite{
	private int playerImgWidth;
	private int playerImgHeight;
	boolean alive =true;
	int nestStatus;
	int health;
	QuizQ currQ;
	boolean inf;
	int iFrame;
	
	Player(){
		lane = Lane.Mid;
		self = 'O';
		xloc = 7;
		alive = true;
		nestStatus =0;
		health = 250;
		inf = false;
	}
	
	Player(int x, int y) {
		xloc = x;
		yloc = y;
	}
	public void setInf() {this.inf = true;
	this.iFrame =5;}
	public int getXloc() { // getters and setters
		return xloc;
	}
	
	public void setXloc(int x) {
		xloc = x;
	}
	public void setHealth(int h) {this.health =h;}
	public int getYloc() {
		return yloc;
	}
	public void setAlive(boolean a) {this.alive = a;}
	public void setYloc(int y) {
		yloc = y;
	}
	
	public int getImgWidth() {
		return playerImgWidth;
	}
	
	public int getImgHeight() {
		return playerImgHeight;
	}
	public void collisionCheck(Sprite e) {
		if(this.xloc == e.xloc && this.lane == e.lane) {
			if(e instanceof Obstacle) {
				this.setAlive(false);
				this.health = 0;
			}
			else if (e instanceof Food) {
				e.setAlive(false);
				e.self = ' ';
				this.health +=10;
			}
			else if (e instanceof NestPiece) {
				e.setAlive(false);
				e.self = ' ';
				this.nestStatus +=1;
			}
				
		}
	}
	
	public void changeLane(String c) {
		if(c.contentEquals("w")) {
			if(this.lane == Lane.Top) {}
			else if(this.lane == Lane.Mid) {lane = Lane.Top;}
			else if(this.lane == Lane.Bottom) {lane = Lane.Mid;}
		}
		else if(c.equals("s")) {
			if(this.lane == Lane.Top) {lane = Lane.Mid;}
			else if(this.lane == Lane.Mid) {lane = Lane.Bottom;}
			else if(this.lane == Lane.Bottom) {}
		}
		else {}
	}
	
	public String Stats() {
		return " Bird is " +alive+ ", HP = " +this.health + " and Nest Status = " +nestStatus+"/5";
		
	}
	
	public void death() {
		this.setAlive(false);
		currQ = new QuizQ();
		
	}
	
	public void update(Collection<Sprite> elem) {
		if(inf && iFrame>0) {
			iFrame -=1; 
			return;}
		else {
			inf = false;
			for(Sprite e :elem) 
				this.collisionCheck(e);
			this.health = this.health -1;
			if (this.health <= 0) {
				this.self = 'D'; 
				this.death();
		}
		}
	}
	
}
