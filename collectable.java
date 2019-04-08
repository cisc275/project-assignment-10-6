package birdyRun;

public class collectable {
	private boolean safe; // collectables have size, nutritional value and safety level
	private int xsize;
	private int ysize;
	private int value;
	
	public int getXSize() { // getters and setters
		return this.xsize;
	}
	public void setXSize(int x) {
		this.xsize = x;
	}
	public boolean getSafe() {
		return safe;
	}
	public void setSafe(boolean s) {
		this.safe = s;
	}
	public int getYSize() {
		return this.ysize;
	}
	public void setYSize(int y) {
		this.ysize = y;
	}
	public int getValue() {
		return this.value;
	}
	public void setValue(int v) {
		this.value = v;
	}
	
}
