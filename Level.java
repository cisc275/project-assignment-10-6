package birdyRun;

public class Level {
	private Entity[] threats; // Level has threats, player, quiz, map and scroll speed
	private Entity player;
	private QuizQ [] quiz;
	private MiniMap map;
	private int scrollSpeed;
	
	public Entity[] getThreats() {  // getters and setters
		return threats;
	}
	public void setThreats(Entity[] threats) {
		this.threats = threats;
	}
	public Entity getPlayer() {
		return player;
	}
	public void setPlayer(Entity player) {
		this.player = player;
	}
	public QuizQ [] getQuiz() {
		return quiz;
	}
	public void setQuiz(QuizQ [] quiz) {
		this.quiz = quiz;
	}
	public MiniMap getMap() {
		return map;
	}
	public void setMap(MiniMap map) {
		this.map = map;
	}
	public int getScrollSpeed() {
		return scrollSpeed;
	}
	public void setScrollSpeed(int scrollSpeed) {
		this.scrollSpeed = scrollSpeed;
	}
	public boolean whichBird() {  // returns true if bird is non-migratory
		if (player.getName() == "Red Clipper") {
			return true;
		}else {
			return false;
		}
	}
	//public void nestBuild() {}
}
