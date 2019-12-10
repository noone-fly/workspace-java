package com.pierre.checkbox;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class CheckBoxText {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				CheckBoxFrame frame = new CheckBoxFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class CheckBoxFrame extends JFrame{
	
	public static final int DEFAULT_WIDTH = 500;
	public static final int DEFAULT_HEIGHT = 500;
	
	private JLabel label;
	private JCheckBox bold;
	private JCheckBox italic;
	private static final int FONTSIZE = 12;
	
	public CheckBoxFrame() {
		setTitle("CheckBoxTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		//add the sample text label
		label = new JLabel("The quick brown fox jumps ove the lazy dog.");
		label.setFont(new Font("Serif", Font.PLAIN, FONTSIZE));
		add(label, BorderLayout.CENTER);
		
		// this listener sets the font attribute of
		// the label to the check box state
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int mode = 0;
				if (bold.isSelected()) {
					mode += Font.BOLD;
				}
				if (italic.isSelected()) {
					mode += Font.ITALIC;
				}
				label.setFont(new Font("Serif", mode, FONTSIZE));
			}
		};
		
		//add the check boxes
		JPanel buttonPanel = new JPanel();
		bold = new JCheckBox("Bold");
		bold.addActionListener(listener);
		buttonPanel.add(bold);
		
		italic = new JCheckBox("Italic");
		italic.addActionListener(listener);
		buttonPanel.add(italic);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
}
