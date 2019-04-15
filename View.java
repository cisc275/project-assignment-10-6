//package birdyRun;

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.JFrame.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;
import javax.swing.JButton;
import java.awt.event.*;
import java.awt.BorderLayout;


public class View extends JFrame{
	public boolean birdType; //Migratory or non-migratory
	private BufferedImage background;
	int scrollSpeed = 10;
	private BufferedImage player;
	private BufferedImage threat;
	final int frameCount = 10;
	int xloc = 100;
    int yloc = 100;
    final int xIncr = 1;
    final int yIncr = 1;
    int picSize = 165;
    final int drawDelay = 30; //msec
	int picNum = 0;

	
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	JFrame frame = new JFrame("Bird Game");
	DrawPanel drawPanel = new DrawPanel();
    Action drawAction;
	
    public View() {
    	background = createImage("Images/GameBackground.jpg");
		frame.add(drawPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.pack();
		frame.setVisible(true);
    }	
	
    @SuppressWarnings("serial")
	private class DrawPanel extends JPanel {
    	int picNum = 0;

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
	    	g.drawImage(background, 0, 0, getWidth(), getHeight(),  this);
		}

	}
	
	public void update(int x, int y) {}
	private BufferedImage createImage(String img) {
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(img));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
	}
	

	
	public void backScroll() {}
	public void death() {}
	public void pop() {}
	public static void main(String[] args) {
		Controller a = new Controller();
		a.start();
		/*EventQueue.invokeLater(new Runnable(){
			public void run(){
				addStopListener(a);
			}
		});*/
	}
	public int getPicSize() {
		return picSize;
	}

}
