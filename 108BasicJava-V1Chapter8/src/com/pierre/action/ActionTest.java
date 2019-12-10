package com.pierre.action;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class ActionTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame frame = new ActionFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class ActionFrame extends JFrame {
	private JPanel buttonPanel;
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 300;

	public ActionFrame() {
		// TODO Auto-generated constructor stub
		setTitle("ActionTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		buttonPanel = new JPanel();

		// define actions
		Action yellowAction = new ColorAction("Yellow", new ImageIcon("yellow-ball.gif"), Color.YELLOW);
		Action blueAction = new ColorAction("Blue", new ImageIcon("blue-ball.gif"), Color.BLUE);
		Action redAction = new ColorAction("Red", new ImageIcon("red-ball.gif"), Color.RED);

		// add buttons for these actions
		buttonPanel.add(new JButton(yellowAction));
		buttonPanel.add(new JButton(blueAction));
		buttonPanel.add(new JButton(redAction));

		// add panel to frame
		add(buttonPanel);

		// associate the Y,B,R keys with names
		InputMap iMap = buttonPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		iMap.put(KeyStroke.getKeyStroke("ctrl Y"), "panel.yellow");
		iMap.put(KeyStroke.getKeyStroke("ctrl B"), "panel.blue");
		iMap.put(KeyStroke.getKeyStroke("ctrl R"), "panel.red");

		// associate the names with actions
		ActionMap aMap = buttonPanel.getActionMap();
		aMap.put("panel.yellow", yellowAction);
		aMap.put("panel.blue", blueAction);
		aMap.put("panel.red", redAction);
	}

	public class ColorAction extends AbstractAction {
		public ColorAction(String name, Icon icon, Color c) {
			putValue(Action.NAME, name);
			putValue(Action.SMALL_ICON, icon);
			putValue("color", c);
			putValue(Action.SHORT_DESCRIPTION, "Set panel color to " + name.toLowerCase());
		}

		public void actionPerformed(ActionEvent event) {
			Color c = (Color) getValue("color");
			buttonPanel.setBackground(c);
		}
	}
}