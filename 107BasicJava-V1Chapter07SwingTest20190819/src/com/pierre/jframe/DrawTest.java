package com.pierre.jframe;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class DrawTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DrawFrame frame = new DrawFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

//创建一个窗口，并在构造函数中设置
class DrawFrame extends JFrame{
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
	public DrawFrame() {
		setTitle("drawtest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//添加画版 到 frame
		DrawComponent component = new DrawComponent();
		add(component);
	}
}

//创建一个组件，显示矩形和椭圆
class DrawComponent extends JComponent {
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	public void paintComponent(Graphics graphics) {
		//Graphics2D 保存用于绘制图像和文本的设置, 包含一系列draw 绘制方法，然后把这个图像对象传给画板的 paintComponent方法，画板上就有图形或文本了， 然后把画板add 给窗口。
		Graphics2D graphics2d = (Graphics2D) graphics;
		
		//画矩形
		double leftX = 100;
		double topY = 100;
		double width = 200;
		double height = 150;
		Rectangle2D rectangle2d = new Rectangle2D.Double(leftX, topY, width, height);
		graphics2d.draw(rectangle2d);
		
		//画椭圆
		Ellipse2D ellipse2d = new Ellipse2D.Double();
		ellipse2d.setFrame(rectangle2d);
		graphics2d.draw(ellipse2d);
		
		//画斜线
		graphics2d.draw(new Line2D.Double(leftX, topY, leftX+width, topY+height));
		
		//画圆
		double certerX = rectangle2d.getCenterX();
		double certerY = rectangle2d.getCenterY();
		double radius = 150;
		
		Ellipse2D circle = new Ellipse2D.Double();
		circle.setFrameFromCenter(certerX, certerY, certerX+radius, certerY+radius);
		graphics2d.draw(circle);
	}
}


