package birdyRun;

public class MiniMap {
	//private loaded img; // minimaps have differences based on image and bird type
	private boolean birdtype; 
	
	public void setBirdType(boolean b) { // setters and getters
		this.birdtype = b;
	}
	public boolean getBirdType() {
		return birdtype;
	}
}
