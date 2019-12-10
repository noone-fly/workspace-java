package com.pierre.text;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TextComponentTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new TextComponentFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class TextComponentFrame extends JFrame{

	public static final int DEFAULT_WIDTH = 500;
	public static final int DEFAULT_HEIGHT = 500;
	
	public TextComponentFrame() {
		// TODO Auto-generated constructor stub
		setTitle("TextComponentText");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		final JTextField textField = new JTextField();
		final JPasswordField passwordField = new JPasswordField();
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(2,2));
		northPanel.add(new JLabel("User name: ", SwingConstants.RIGHT));
		northPanel.add(textField);
		northPanel.add(new JLabel("Password : ", SwingConstants.RIGHT));
		northPanel.add(passwordField);
		
		add(northPanel, BorderLayout.NORTH);
		
		final JTextArea textArea = new JTextArea(8, 40);
		JScrollPane scrollPane = new JScrollPane(textArea);
		
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel southPanel = new JPanel();
		JButton insertButton = new JButton("Insert");
		southPanel.add(insertButton);
		insertButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textArea.append("User name: " + textField.getText() + " Password: "
						+ new String(passwordField.getPassword() + "\n"));
			}
		});
		
		add(southPanel, BorderLayout.SOUTH);
	}
}