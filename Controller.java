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
		view.startClapperTutorial.addActionListener(this);
		view.startOspreyTutorial.addActionListener(this);
	}
	
    //run the simulation
	public void start(){
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				model.updateLocation();
				view.update(model.getSprites(), model.getPlayer(), model.getGameOver());
				model.setGameOver(view.getGameOver());
			}
		};
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Timer t = new Timer(15, drawAction);
				t.start();
			}
		});
	}
	
	public void startTutorial(){
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
	
			}
		};
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				Timer t = new Timer(15, drawAction);
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
			view.getPlayer().setMigratory(false);
			model.setLevelProgress(0);
			view.removeMenu();
			view.clapperdrawPanel.addKeyListener(this);
			start();
        }
		else if (action.equals("Osprey")) {
			view.getPlayer().setMigratory(true);
			model.setLevelProgress(0);
			view.removeMenu();
			view.ospreydrawPanel.addKeyListener(this);
			start();
		}
		else if (action.equals("Clapper Tutorial")) {
			view.getPlayer().setMigratory(false);
			model.setLevelProgress(0);
			view.removeMenu();
			view.clapperdrawPanel.addKeyListener(this);
			startTutorial();
		}
		else if (action.equals("Osprey Tutorial")) {
			view.getPlayer().setMigratory(true);
			model.setLevelProgress(0);
			view.removeMenu();
			view.ospreydrawPanel.addKeyListener(this);
			startTutorial();
		}
	}
}