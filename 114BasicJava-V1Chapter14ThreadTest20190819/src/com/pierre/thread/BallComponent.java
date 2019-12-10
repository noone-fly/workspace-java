package com.pierre.thread;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class BallComponent extends JPanel{
	
	private ArrayList<Ball> balls = new ArrayList<Ball>();
	
	public void add(Ball ball) {
		balls.add(ball);
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics); 
		Graphics2D g2 = (Graphics2D)graphics;
		for(Ball ball:balls) {
			g2.fill(ball.getShape());
		}
	}
}
