package com.pierre;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new PanelFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class PanelFrame extends JFrame{

	public static final int DEFAULT_WIDTH = 700;
	public static final int DEFAULT_HEIGHT = 500;
	
	public PanelFrame() {
		// TODO Auto-generated constructor stub
		setTitle("Agora Tools");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		JPanel jP = new JPanel();
		jP.setLayout(null);// 设置空布局，即绝对布局
 
		JButton jButton1 = new JButton("测试1");
		jButton1.setBounds(0, 0, 100, 35);// 设置位置及大小
		jP.add(jButton1);
 
		JButton jButton2 = new JButton("测试2");
		jButton2.setBounds(0, 30, 100, 35);// 设置位置及大小
		jP.add(jButton2);
 
		JButton jButton3 = new JButton("测试3");
		jButton3.setBounds(0, 60, 100, 35);// 设置位置及大小
		jP.add(jButton3);
		
		add(jP);
	}
}
