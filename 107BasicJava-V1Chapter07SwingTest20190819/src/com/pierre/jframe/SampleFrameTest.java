package com.pierre.jframe;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.*;

/**
 * 
 * @author chenpiyang
 * 默认情况，窗体大小为 0*0，这里定义一个子类，设置窗体大小为 300*200
 * 在每个Swing程序中，有两点需要注意
 * 1，所有Swing组件必现由事件调度线程（event dispatch thread）进行配置，线程将鼠标点击和键盘敲击控制转移到用户接口组件
 *   不在主线程初始化，而在事件调度线程中初始化，是为安全考虑，
 *   EventQueue.invokeLater(new Runnable(){
 *     public void run(){
 *       statements
 *     }
 *   });
 * 2，定义一个用户关闭该框架时的响应动作：frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 *   
 *   创建框架之初是不可见的，需要调用 frame.setVisible(true);
 *   
 *   退出main方法，只是停止主线程，事件调度线程依然保持激活状态，只有关闭框架或调 System.exit方法终止程序
 */
public class SampleFrameTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleFrame frame = new SimpleFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class SimpleFrame extends JFrame{
	//public static final int DEFAULT_WIDTH = 300;
	//public static final int DEFAULT_HEIGHT = 200;
	//获取屏幕大小
	Toolkit toolKit = Toolkit.getDefaultToolkit();
	Dimension screenSize = toolKit.getScreenSize();
	
	Image image = toolKit.getImage("2017-04-07 204655.jpg");
	//setIconImage(image);
	
	
	int screenWidth = screenSize.width;
	int screenHeight = screenSize.height;
	
	public SimpleFrame() {
		//setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//将框架大小设置为屏幕的50%
		setSize(screenWidth / 2, screenHeight / 2);
		//告知窗口系统定位框架
		setLocationByPlatform(true);
		setIconImage(image);
		setTitle("test");
	}
}
