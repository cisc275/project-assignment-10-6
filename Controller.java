//package birdyRun;

import java.awt.event.*;

public class Controller implements KeyListener { 
	private Model model;
	private View view;
	int lastkey;
	
	
	public Controller(){
		view = new View();
		model = new Model(view.getScreenSize(), view.getPlayer(), view.getImgs());
	}
	
    //run the simulation
	public void start(){
		for(int i = 0; i < 10000; i++) {
			model.updateLocation();
			view.update(model.getSprites(), model.getPlayer());
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
			if (key == KeyEvent.VK_UP) {
				model.move("up");
			}
			else if (key == KeyEvent.VK_DOWN) {
				model.move("down");
			}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP) {
			model.move("stop");
		}
		else if (key == KeyEvent.VK_DOWN) {
			model.move("stop");
		}
	}
	
	public void keyTyped(KeyEvent e) {}
}
