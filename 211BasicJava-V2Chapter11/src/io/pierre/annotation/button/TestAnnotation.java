package io.pierre.annotation.button;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class TestAnnotation {

	public static void main(String[] args) {
		// 在每个Swing程序中，有两点需要注意
		// 1，所有Swing组件必现由事件调度线程（event dispatch thread）进行配置，线程将鼠标点击和键盘敲击控制转移到用户接口组件
		//   不在主线程初始化，而在事件调度线程中初始化，是为安全考虑，
		//awt的事件处理线程会按照队列的顺序依次调用每个待处理的事件来运行
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new ButtoneFrame();
				//定义一个用户关闭该框架时的响应动作：
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//创建框架之初是不可见的，需要调用
				frame.setVisible(true);
			}
		});
	}

}
