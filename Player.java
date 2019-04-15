//package birdyRun;

public class Player extends Sprite{
	private int playerImgWidth;
	private int playerImgHeight;
	
	Player(int x, int y) {
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
		return playerImgWidth;
	}
	
	public int getImgHeight() {
		return playerImgHeight;
	}
	
	
}
