//package birdyRun;

import java.awt.event.*;
import java.awt.EventQueue;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;

public class Controller implements KeyListener, ActionListener { 
	private Model model;
	private View view;
	int lastkey;
	Action drawAction;
	
	
	public Controller(){
		view = new View();
		model = new Model(view.getScreenSize(), view.getPlayer(), view.getNest(), view.getImgs());
		view.startClapper.addActionListener(this);
		view.startOsprey.addActionListener(this);
	}
	
    //run the simulation
	public void start(){
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				model.updateLocation();
				view.update(model.getSprites(), model.getPlayer());
			}
		};
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Timer t = new Timer(0, drawAction);
				t.start();
			}
		});
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
	
	public void keyReleased(KeyEvent e) {}
	
	public void keyTyped(KeyEvent e) {}
	
	public void actionPerformed(ActionEvent ae) {
		String action = ae.getActionCommand();
        if (action.equals("Clapper Rail")) {
			view.setMigratoryStatus(false);
			model.setMigratoryStatus(false);
			view.removeMenu();
			view.clapperdrawPanel.addKeyListener(this);
			start();
        }
		else if (action.equals("Osprey")) {
			view.setMigratoryStatus(true);
			model.setMigratoryStatus(true);
			view.removeMenu();
			view.ospreydrawPanel.addKeyListener(this);
			start();
		}
	}
}
