package birdyRun;

public class Enemy extends Entity{
	private String name; // enemies have name, damage value, size and type
	private int damage;
	private int xsize;
	private int ysize;
	private String type;
	
	
	public void setXSize(int x) { //getters and setters
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
	public void setName(String n) {
		this.name = n;
	}
	public String getName() {
		return name;
	}
	public void setDamage(int d) {
		this.damage = d;
	}
	public int getDamage() {
		return damage;
	}
	public void setType(String t) {
		this.type = t;
	}
	public String getType() {
		return type;
	}
	public void attack(Player p) {  // enemies can damage the player
		p.setHealth(p.getHealth() - this.damage);
	}
}
