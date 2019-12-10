package com.pierre.muiltThread;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.pierre.thread.Ball;
import com.pierre.thread.BallComponent;

public class BounceThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new BounceFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

// a runnable that animates a bouncing ball
class BallRunnable implements Runnable{
	private Ball ball;
	private Component component;
    public static final int STEPS = 4000;
    public static final int DELAY = 12;
    
    public BallRunnable(Ball aBall, Component aComponent) {
		ball = aBall;
		component = aComponent;
	}
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for (int i = 1; i < STEPS; i++) {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

//The frame with panel and buttons
class BounceFrame extends JFrame{
	private BallComponent component;
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 4000;
	public static final int DELAY = 15;
	public BounceFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("bounce thread");
		component = new BallComponent();
		add(component, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel, "Start", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addBall();
			}
			
		});
		addButton(buttonPanel, "Close", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	public void addBall() {
		Ball ball = new Ball();
		component.add(ball);
		Runnable  runnable = new BallRunnable(ball, component);
		Thread thread = new Thread(runnable);
		thread.start();
		
	}
}


