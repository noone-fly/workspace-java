package com.pierre.swinglayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GridBagDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new ToolFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class ToolFrame extends JFrame{
	

	public static final int DEFAULT_WIDTH = 700;
	public static final int DEFAULT_HEIGHT = 500;
	
	JButton button1 = new JButton("open");
	JButton button2 = new JButton("save");
	JButton button3 = new JButton("save as");
	JPanel panel4 = new JPanel();
	String[] str= {"CreateToken","VarifyToken"};
	JComboBox comboBox5 = new JComboBox(str);
	JTextField textField6 = new JTextField();
	JButton button7 = new JButton("clear");
	JList list8 = new JList(str);
	JTextArea textArea9 = new JTextArea();
	
	public ToolFrame() {
		setTitle("Test");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		
		textArea9.setBackground(Color.WHITE);
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		this.add(button1);
		this.add(button2);
		button2.setBackground(Color.BLACK);
		this.add(button3);
		this.add(panel4);
		panel4.setBackground(Color.CYAN);
		this.add(comboBox5);
		
		this.add(textField6);
		textField6.setBackground(Color.GRAY);
		this.add(button7);
		this.add(list8);
		list8.setBackground(Color.PINK);
		this.add(textArea9);
		
		// 定义一个GridBagConstraints，
        // 是用来控制添加进的组件的显示位置
		GridBagConstraints constraints = new GridBagConstraints();
		
		// 该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        // NONE：不调整组件大小。
        // HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        // VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        // BOTH：使组件完全填满其显示区域。
		constraints.fill = GridBagConstraints.BOTH;
		
		// 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
		constraints.gridwidth = 1;
		// 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		constraints.weightx = 0;
		// 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
		constraints.weighty = 0;
		//设置组件
		layout.setConstraints(button1, constraints);
		
		constraints.gridwidth = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		layout.setConstraints(button2, constraints);
		
		constraints.gridwidth = 1;
		constraints.weightx = 0;
		constraints.weighty = 0;
		layout.setConstraints(button3, constraints);
		
		constraints.gridwidth = 0;
		constraints.weightx = 0;
		constraints.weighty = 0;
		
		layout.setConstraints(panel4, constraints);
		
		
		constraints.gridwidth = 2;
		constraints.weightx = 0;
		constraints.weighty = 0;
		layout.setConstraints(comboBox5, constraints);
		
		constraints.gridwidth = 4;
		constraints.weightx = 1;
		constraints.weighty = 0;
		layout.setConstraints(textField6, constraints);
		
		constraints.gridwidth = 0;
		constraints.weightx = 0;
		constraints.weighty = 0;
		layout.setConstraints(button7, constraints);
		
		constraints.gridwidth = 2;
		constraints.weightx = 0;
		constraints.weighty = 1;
		layout.setConstraints(list8, constraints);
		
		constraints.gridwidth = 5;
		constraints.weightx = 0;
		constraints.weighty = 1;
		layout.setConstraints(textArea9, constraints);
	}
}






