//package birdyRun;


public class Food extends Sprite{		//food class for food objects

	private int foodImgHeight;
	private int foodImgWidth;
	
	food(int x, int y) {
		xloc = x;
		yloc = y;
	}
	
	public int getXloc() { // getters and setters
		return xloc;
	}
	public void setXloc(int x) {
		xloc = x;
	}

	public int getYloc() {
		return yloc;
	}
	public void setYloc(int y) {
		yloc = y;
	}
	public int getImgWidth() {
		return foodImgWidth;
	}
	public int getImgHeight() {
		return foodImgHeight;
	}

}