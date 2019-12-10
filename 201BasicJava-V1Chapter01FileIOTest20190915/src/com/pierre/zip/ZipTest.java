package com.pierre.zip;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				ZipTestFrame frame = new ZipTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame with a text area to show the contents of a file inside a ZIP archive, 
 * a combobox to select different files in the archive, and a menu to load a new archive.
 */

class ZipTestFrame extends JFrame{
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 300;
	private JComboBox fileCombo;
	private JTextArea fileText;
	private String zipname;
	
	String filestr = System.getProperty("user.dir") + "/src/com/pierre/zip/";
	
	public ZipTestFrame() {
		setTitle("ZipTest");
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		
		//add the menu and the open and exit menu items
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		
		JMenuItem openItem = new JMenuItem("Open");
		menu.add(openItem);
		openItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(filestr));
				int r = chooser.showOpenDialog(ZipTestFrame.this);
				if (r == JFileChooser.APPROVE_OPTION) {
					zipname = chooser.getSelectedFile().getPath();
					fileCombo.removeAllItems();
					scanZipFile();
				}
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		menu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menuBar.add(menu);
		setJMenuBar(menuBar);
		
		//add the text area and combo box
		fileText = new JTextArea();
		fileCombo = new JComboBox();
		fileCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loadZipFile((String)fileCombo.getSelectedItem());
			}
		});
		
		add(fileCombo, BorderLayout.SOUTH);
		add(new JScrollPane(fileText), BorderLayout.CENTER);
		
	}
	
	/**
	 * Scans the contents of the zip archive and populates the combo box
	 */
	public void scanZipFile() {
		new SwingWorker<Void, String>() {

			@Override
			protected Void doInBackground() throws Exception {
				ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname));
				ZipEntry entry;
				while ((entry = zin.getNextEntry()) != null) {
					publish(entry.getName());
					zin.closeEntry();
				}
				zin.close();
				return null;
			}
			protected void process(List<String> names) {
				for (String name : names) {
					fileCombo.addItem(name);
				}
			}
		}.execute();
	}
	/**
	 * Loads a file from the zip archive into the text area
	 */
	public void loadZipFile(final String name) {
		fileCombo.setEnabled(false);
		fileText.setText("");
		new SwingWorker<Void, Void>() {

			@Override
			protected Void doInBackground() throws Exception {
				try {
					ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname));
					ZipEntry entry;
					
					//find entry with matching name in archive
					while ((entry = zin.getNextEntry()) != null) {
						if (entry.getName().equals(name)) {
							//read entry into text area
							Scanner in = new Scanner(zin);
							while (in.hasNextLine()) {
								fileText.append(in.nextLine());
								fileText.append("\n");
							}
						}
						zin.closeEntry();
					}
					zin.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}
			
			protected void done() {
				fileCombo.setEnabled(true);
			}
			
		}.execute();
	}
}