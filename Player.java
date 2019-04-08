package birdyRun;

public class Player extends Entity{
	private int health;  // Player has health, score, nest level and danger based on attacker and progress
	private int score;
	private int nestLevel;
	private boolean danger;
	
	public void setHealth(int h) {  // getters and setters 
		this.health = h;
	}
	public int getHealth() {  
		return health;
	}
	public void setScore(int s) {
		this.score = s;
	}
	public int getScore() {
		return score;
	}
	public void setNestLevel(int n) {
		this.nestLevel = n;
	}
	public int getNestLevel() {
		return nestLevel;
	}
	public void setDanger(boolean d) {
		this.danger = d;
	}
	public boolean getDanger() {
		return danger;
	}
	 
	public void flyup() {  // player can move up lanes
		if (super.getCurrentLane() == Lane.Bottom) {
			super.setCurrentLane(Lane.Mid);
		}else {
			super.setCurrentLane(Lane.Top);
		}
	}
	public void flydown() {  // player can move down lanes
		if (super.getCurrentLane() == Lane.Top) {
			super.setCurrentLane(Lane.Mid);
		}else {
			super.setCurrentLane(Lane.Bottom);
		}
	}
	public void eat(Food f) {  // player can eat collectables that are food
		if (f.getSafe() == true) {
			this.health += f.getValue();
		}else {
			this.health -= f.getValue();
		}
	}
	public void scorer(NestPiece n) { // player can collect nest pieces to increase score and nest level
		if (n.getSafe() == true) {
			this.score++;
			this.nestLevel++;
		}else {
			this.score--;
		}
	}
}
