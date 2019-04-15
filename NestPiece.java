//package birdyRun;


public class NestPiece extends Sprite { // nest pieces can be collected
	private int nestpieceImgHeight;
	private int nestpieceImgWidth;
	
	NestPiece(int x, int y) {
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
		return nestpieceImgWidth;
	}
	public int getImgHeight() {
		return nestpieceImgHeight;
	}	
}
