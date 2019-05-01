//package birdyRun;

import java.awt.event.*;

public class Controller implements KeyListener { 
	private Model model;
	private View view;
	int lastkey;
	
	
	public Controller(){
		view = new View();
		model = new Model(view.getSprites(), view.getPlayer());
	}
	
    //run the simulation
	public void start(){
		for(int i = 0; i < 5000; i++) {
			model.updateLocation();
			view.update(model.getSprites());
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (lastkey != e.getKeyCode()) {
			if (key == KeyEvent.VK_UP) {
				model.move();
			}
			else if (key == KeyEvent.VK_DOWN) {
				model.move();
			}
		}
		lastkey = e.getKeyCode();
	}
	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e) {}
}
