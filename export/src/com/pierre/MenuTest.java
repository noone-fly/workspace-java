package com.pierre;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.lang.reflect.Field;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class MenuTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new MenuFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}


class MenuFrame extends JFrame{

	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 600;
	
	private Action saveAction;
	private Action saveAsAction;
	private JCheckBoxMenuItem readonlyItem;
	private JPopupMenu popup;
	
	public MenuFrame() {
		setTitle("MenuTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//
		JMenu fileMenu = new JMenu("File");
		//创建连接到指定 Action 对象的新菜单项，并将其追加到此菜单的末尾
		fileMenu.add(new TestAction("New"));
		
		//demonstrates accelerateors
		JMenuItem openItem = fileMenu.add(new TestAction("Open"));
		//为open菜单项定义一个加速器 Accelerator，
		openItem.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		//将新分隔符追加到菜单的末尾
		fileMenu.addSeparator();
		
		saveAction = new TestAction("Save");
		JMenuItem saveItem = fileMenu.add(saveAction);
		saveItem.setAccelerator(KeyStroke.getKeyStroke("ctrl s"));
		
		//都用TestAction的行为动作
		saveAsAction = new TestAction("Save As");
		fileMenu.add(saveAsAction);
		fileMenu.addSeparator();
		
		//直接用匿名类定义一个行为， 自己定义一个System.exit(0) 的行为
		fileMenu.add(new AbstractAction("Exit") {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//demonstrate check box and radio button menus
		//为readonly 定义独有行为动作
		readonlyItem = new JCheckBoxMenuItem("Read-only");
		readonlyItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean saveOk = !readonlyItem.isSelected();
				saveAction.setEnabled(saveOk);
				saveAsAction.setEnabled(saveOk);
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButtonMenuItem insertItem = new JRadioButtonMenuItem("Insert");
		insertItem.setSelected(true);
		JRadioButtonMenuItem overtypeItem = new JRadioButtonMenuItem("Overtype");
		
		group.add(insertItem);
		group.add(overtypeItem);
		
		//demonstrate icons 展示图片
		Action cutAction = new TestAction("Cut");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("cut.gif"));
		Action copyAction = new TestAction("Copy");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("copy.gif"));
		Action pasteAction = new TestAction("Paste");
		cutAction.putValue(Action.SMALL_ICON, new ImageIcon("paste.gif"));
		
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		
		//demonstrate nested menus 展示嵌套菜单
		JMenu optionMenu = new JMenu("Options");
		optionMenu.add(readonlyItem);
		optionMenu.addSeparator();
		optionMenu.add(insertItem);
		optionMenu.add(overtypeItem);
		
		editMenu.addSeparator();
		editMenu.add(optionMenu);
		
		//demonstrate mnemonics
		JMenu helpMenu = new JMenu("Help");
		//在当前型号上设置键盘助记符
		helpMenu.setMnemonic('H');
		
		JMenuItem indexItem = new JMenuItem("Index");
		indexItem.setMnemonic('I');
		helpMenu.add(indexItem);
		
		// you can also add the mnemonic key to an action
		Action aboutAction = new TestAction("About");
		aboutAction.putValue(Action.MNEMONIC_KEY, new Integer('A'));
		helpMenu.add(aboutAction);
		
		//add all top-level menus to menu bar
		//创建一个菜单栏
		JMenuBar menuBar = new JMenuBar();
		//void javax.swing.JFrame.setJMenuBar(JMenuBar menubar)
		//JFrame 的方法，设置此帧的菜单栏。添加到框架上，并没有指定位置，就是在frame顶部
		setJMenuBar(menuBar);
		//把三个菜单项添加到菜单栏
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		//demonstrate pop-ups
		popup = new JPopupMenu();
		popup.add(cutAction);
		popup.add(copyAction);
		popup.add(pasteAction);
		
		JPanel panel = new JPanel();
		panel.setComponentPopupMenu(popup);
		add(panel);
		
		//the following line is a workaround for bug 4966109
		panel.addMouseListener(new MouseAdapter() {
		});
		
	}
}

class TestAction extends AbstractAction{
	public TestAction(String name) {
		// TODO Auto-generated constructor stub
		super(name);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println(getValue(javax.swing.Action.NAME) + " selected.");
	}
}