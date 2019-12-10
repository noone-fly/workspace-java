package com.pierre.frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;

import com.pierre.actionlistener.ActionListenerTools;
import com.pierre.frame.cardPanel.StartLocalRecord;
import com.pierre.frame.cardPanel.WelcomPanel;

public class MainFrame extends JFrame {
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 750;
	private JPanel left = null;
	private JPanel center = null;
	private JTextArea displayArea = null;
	// 定义卡片布局
	private CardLayout cardLayout = null;
	
	
	public MainFrame() {
		setTitle("Pierre DIY");  //frame title
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);  //frame width and height
		
		//左右两个面板
		left = new JPanel();
		center = new JPanel();
		cardLayout = new CardLayout();
		center.setLayout(cardLayout);//给面板设置卡片布局
		
		//左边的树结构
		JTree tree = BuildTree.buildTree();
        //把tree加入左边JPanel 并设置大小
        left.add(new JScrollPane(tree) {
        		public Dimension getPreferredSize() {return new Dimension(140, 720);}
        	});
        
		//右边面板是卡片布局， 带过来卡片布局中的tag
        //JPanel cardPanel = BuildCardLayout.buildCardLayoutPanel();
        //在卡片布局中添加所有子面板，但当前只显示最上层的面板
        buildCardLayoutPanel(center);
        
//		center.add(new JScrollPane(cardPanel){
//			//设置右边面板的大小
//	    		public Dimension getPreferredSize() {return new Dimension(640, 720);}
//	    	});
		
		//点击树节点，触发监听事件，显示对应的子面板
		ActionListenerTools.TreeSelectionListenerFun(tree, center, cardLayout);

		//把两个panel面板加入到frame中，并确定 BorderLayout
        add(left, BorderLayout.WEST);
        //add(cardPanel, BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);
	}
	
	private void buildCardLayoutPanel(JPanel panel) {
		
		// 创建需要放入到卡片布局的子面板，
		JPanel welcomePanel = new WelcomPanel().returnPanel();
		JPanel startLocalRecord = new StartLocalRecord().returnPanel();
		
		// 把定义好的子面板放入卡片布局中,每个子面板对应一个tag  
		panel.add("welcomeTag", welcomePanel);
		panel.add("startLocalRecordTag", startLocalRecord);
		//return cardPanel;
	}
}
