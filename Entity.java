package birdyRun;

public class Entity {
	private String name; // entities have name, size, location, and lane they are in
	private int xsize;
	private int ysize;
	private int xloc;
	private int yloc;
	private Lane currentLane;
	
	public void setName(String n) { // getters and setters
		this.name = n;
	}
	public String getName() {
		return name;
	}
	public void setXSize(int x) { 
		this.xsize = x;
	}
	public int getXSize() {
		return xsize;
	}
	public void setYSize(int y) { 
		this.ysize = y;
	}
	public int getYSize() {
		return ysize;
	}
	public void setXLoc(int x) { 
		this.xloc = x;
	}
	public int getXLoc() {
		return xloc;
	}
	public void setYLoc(int y) { 
		this.yloc = y;
	}
	public int getYLoc() {
		return yloc;
	}
	public void setCurrentLane(Lane l) { 
		this.currentLane = l;
	}
	public Lane getCurrentLane() {
		return currentLane;
	} 
	public boolean isPlayer() { // returns true if entity is player
		Player p = (Player) this;
		if (p.getHealth() >= 0 ) {
			return true;
		}else {
			return false;
		}
	}
	public void damage(Player p) { // entities can damage other entities
		Enemy e = (Enemy) this;
		e.attack(p);
	}
	public boolean notMoving() {  // true if entity is not moving
		Enemy e = (Enemy) this;
		if (e.getType() == "still") {
			return true;
		}else {
			return false;
		}
	}
}
