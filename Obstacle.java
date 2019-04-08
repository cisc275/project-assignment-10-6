//package birdyRun;

public class Obstacle extends Enemy{
	private int xsize;
	private int ysize;
	private String type;
	private String name;
	
	
	public void setXSize(int x) { //getters and setters
		//this.xsize = x;
	}
	public int getXSize() {
		return 1;
	}
	public void setYSize(int y) { 
		//this.ysize = y;
	}
	public int getYSize() {
		return 1;
	}
	public void setType(String t) {
		//this.type = t;
	}
	public String getType() {
		return "";
	}
	public void setName(String n) {
		//this.name = n;
	}
	public String getName() {
		return "";
	}
	public boolean isUpObstacle() {  // returns whether obstacle is on the bottom (false) or not (true)
		/**
		Entity e = this;
		if (e.getCurrentLane() == Lane.Top || e.getCurrentLane() == Lane.Mid) {
			return true;
		}else {
			return false;
		}
		*/
		return false;
	}
}
