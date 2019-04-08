package birdyRun;

import java.awt.image.BufferedImage;

public class View {
	private BufferedImage background;
	private BufferedImage player;
	private BufferedImage threat;
	
	public BufferedImage getBackground() {
		return background;
	}
	public void setBackground(BufferedImage background) {
		this.background = background;
	}
	public BufferedImage getPlayer() {
		return player;
	}
	public void setPlayer(BufferedImage player) {
		this.player = player;
	}
	public BufferedImage getThreat() {
		return threat;
	}
	public void setThreat(BufferedImage threat) {
		this.threat = threat;
	}
	// public void update(){}
	// public void drawLevel() {}
	// public void backScroll() {}
	// public void death() {}
	// public void pop() {}
	// public void main(String args[]) {}
}
