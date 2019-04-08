//package birdyRun;

public class Enemy extends Entity{ 
	private String name; // enemies have name, damage value, size and type
	private int damage;
	private int xsize;
	private int ysize;
	private String type;
	
	
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
	public void setName(String n) {
		//this.name = n;
	}
	public String getName() {
		return "";
	}
	public void setDamage(int d) {
		//this.damage = d;
	}
	public int getDamage() {
		return 1;
	}
	public void setType(String t) {
		//this.type = t;
	}
	public String getType() {
		return "";
	}
	public void attack(Player p) {  // enemies can damage the player
		//p.setHealth(p.getHealth() - this.damage);
	}
}
