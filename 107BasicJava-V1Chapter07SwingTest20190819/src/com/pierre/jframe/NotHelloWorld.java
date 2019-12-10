package com.pierre.jframe;
import javax.swing.*;
import java.awt.*;

public class NotHelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				NotHelloWorldFrame frame = new NotHelloWorldFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

//创建一个窗口，并在构造函数中设置
class NotHelloWorldFrame extends JFrame{
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 200;
	public NotHelloWorldFrame() {
		setTitle("helloworld");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//添加panel 到 frame
		NotHelloWorldPanel panel = new NotHelloWorldPanel();
		add(panel);
	}
}

//创建一个面板，用于显示文本
class NotHelloWorldPanel extends JPanel{
	public static final int MESSAGE_X = 75;
	public static final int MESSAGE_Y = 100;
	public void paintComponent(Graphics graphics) {
		graphics.drawString("Hello world", MESSAGE_X, MESSAGE_Y);
	}
}

