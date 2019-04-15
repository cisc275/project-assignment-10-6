//package birdyRun;

public class Obstacle extends Sprite {
	private int obstacleImgHeight;
	private int obstacleImgWidth;
	
	Obstacle(int x, int y) {
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
		return obstacleImgWidth;
	}
	public int getImgHeight() {
		return obstacleImgHeight;
	}
}
