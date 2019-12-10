package com.pierre.applet;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class WelcomeApplet extends JApplet{

	public void init() {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				setSize(400,400);
				setLayout(new BorderLayout());
				
				JLabel label = new JLabel(getParameter("greeting"), SwingConstants.CENTER);
				label.setFont(new Font("Serif", Font.BOLD, 18));
				add(label, BorderLayout.CENTER);
				
				JPanel panel = new JPanel();
				
				JButton cayButton = new JButton("Cay Horstmann");
				cayButton.addActionListener(makeAction("https://www.agora.io/cn/"));
				panel.add(cayButton);
				
				JButton garyButton = new JButton("Gary Cornell");
				garyButton.addActionListener(makeAction("mailto:chenpiyang@agora.io"));
				panel.add(garyButton);
				
				add(panel, BorderLayout.SOUTH);
			}
		});
	}
	
	private ActionListener makeAction(final String urlString) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					getAppletContext().showDocument(new URL(urlString));
				} catch (MalformedURLException e2) {
					e2.printStackTrace();
				}
			}
		};
	}

}
