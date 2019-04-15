//package birdyRun;


public class Food extends Sprite{		//food class for food objects

	private int foodImgHeight;
	private int foodImgWidth;
	
	Food(){
		self = '~';
		int rand  = (int)Math.floor(Math.random()*3);
		if(rand == 0)
			lane =Lane.Top;
		else if(rand==1)
			lane = Lane.Mid;
		else
			lane=Lane.Bottom;
		xloc = (int)Math.floor(Math.random()*900)+100;
		
	}
	Food(int x, int y) {
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