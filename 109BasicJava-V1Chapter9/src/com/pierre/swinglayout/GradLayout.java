package com.pierre.swinglayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GradLayout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new TestGradLayout();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

class TestGradLayout extends JFrame {

	public static final int DEFAULT_WIDTH = 500;
	public static final int DEFAULT_HEIGHT = 500;

	private JPanel cardPanel, containbtn; // cardPanel为卡片显示区域， containbtn为按钮显示区域
	private JButton btn1, btn2, btn3, btn4; // 此处定义诗人的按钮
	private JPanel panel1, panel2, panel3, panel4; // 此处定义的面板中显示诗句
	private CardLayout card; // 定义卡片布局
	private JTextArea textArea1, textArea2, textArea3, textArea4; // 定义放诗句的文本域

	public TestGradLayout() {
		// TODO Auto-generated constructor stub

		setTitle("TestGradLayout");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		// 以下为初始化组件
		cardPanel = new JPanel();
		containbtn = new JPanel();
		card = new CardLayout();
		cardPanel.setLayout(card); //设置为卡片布局

		btn1 = new JButton("李白");
		btn2 = new JButton("杜甫");
		btn3 = new JButton("白居易");
		btn4 = new JButton("孟浩然");

		textArea1 = new JTextArea(30, 30);
		textArea2 = new JTextArea(30, 30);
		textArea3 = new JTextArea(30, 30);
		textArea4 = new JTextArea(30, 30);

		// 把定义的按钮放到显示按钮的面板中
		containbtn.add(btn1);
		containbtn.add(btn2);
		containbtn.add(btn3);
		containbtn.add(btn4);

		panel1 = new JPanel();
		// 给按钮添加监听事件
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "b1");
			}
		});
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "b2");
			}
		});
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "b3");
			}
		});
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(cardPanel, "b4");
			}
		});

		// 给定义的文本域中添加诗人相应的诗句
		textArea1.append("日照香炉生紫烟，\r\n");
		textArea1.append("遥看瀑布挂前川。\r\n");
		textArea1.append("飞流直下三千尺，\r\n");
		textArea1.append("疑是银河落九天。");

		textArea2.append("两个黄鹂鸣翠柳，\r\n");
		textArea2.append("一行白鹭上青天。\r\n");
		textArea2.append("窗含西岭千秋雪，\r\n");
		textArea2.append("门泊东吴万里船。");

		textArea3.append("一道残阳铺水中，\r\n");
		textArea3.append("半江瑟瑟半江红。\r\n");
		textArea3.append("可怜九月初三夜，\r\n");
		textArea3.append("露似珍珠月似弓。");

		textArea4.append("春眠不觉晓，\r\n");
		textArea4.append("处处闻啼鸟。\r\n");
		textArea4.append("夜来风雨声，\r\n");
		textArea4.append("花落知多少。");

		panel1.add(textArea1);
		panel2 = new JPanel();
		panel2.add(textArea2);
		panel3 = new JPanel();
		panel3.add(textArea3);
		panel4 = new JPanel();
		panel4.add(textArea4);

		// 把定义好的面板放到卡片布局的组件中
		cardPanel.add("b1", panel1);
		cardPanel.add("b2", panel2);
		cardPanel.add("b3", panel3);
		cardPanel.add("b4", panel4);

		add(containbtn, BorderLayout.NORTH);// 把显示按钮的组件放到上面显示
		add(cardPanel, BorderLayout.CENTER);// 把显示文本域的组件放到中间显示
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/**
		 * e.getActionCommand（）显示的结果是对应组件上的内容标签 card.show(cardPanel, "b1");表示对应组件和显示组件的名称
		 * CardLayout.show(Container parent, String name)：显示卡片的实现方法定义
		 */
		if ("李白".equals(e.getActionCommand())) {
			card.show(cardPanel, "b1");
		}
		if ("杜甫".equals(e.getActionCommand())) {
			card.show(cardPanel, "b2");
		}
		if ("白居易".equals(e.getActionCommand())) {
			card.show(cardPanel, "b3");
		}
		if ("孟浩然".equals(e.getActionCommand())) {
			card.show(cardPanel, "b4");
		}
	}
}