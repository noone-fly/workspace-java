package com.pierre.thread;
import java.awt.geom.*;

public class Ball {
	
	private static final int XSISE = 15; //画一个15像素的小圆
	private static final int YSISE = 15;
	private double x = 0;
	private double y = 0;
	private double dx = 1; //每次移动一个像素
	private double dy = 1;
	
	//moves the ball to the next position, reversing direction if it hits one of the edges
	//移动小球到下一个位置，如果撞到某一边，就反弹。
	public void move(Rectangle2D bounds) {
		x += dx;
		y += dy;
		if(x < bounds.getMinX()) {
			x = bounds.getMinX();
			dx = -dx;
		}
		if(x + XSISE >= bounds.getMaxX()) {
			x = bounds.getMaxX() - XSISE;
			dx = -dx;
		}
		
		if(y < bounds.getMinY()) {
			y = bounds.getMinY();
			dy = -dy; //变成 -1 
		}
		if(y + YSISE >= bounds.getMaxY()) {
			y = bounds.getMaxY() - YSISE;
			dy = -dy;
		}
	}
	//画一个15像素的小圆
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSISE, YSISE);
	}
}
