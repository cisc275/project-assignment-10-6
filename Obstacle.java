//package birdyRun;

public class Obstacle extends Sprite {
	private int obstacleImgHeight;
	private int obstacleImgWidth;
	
	Obstacle(){
		self = 'X';
		int rand  = (int)Math.floor(Math.random()*3);
		if(rand == 0)
			lane =Lane.Top;
		else if(rand==1)
			lane = Lane.Mid;
		else
			lane=Lane.Bottom;
		xloc = (int)Math.floor(Math.random()*975)+25;
	}
	
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
