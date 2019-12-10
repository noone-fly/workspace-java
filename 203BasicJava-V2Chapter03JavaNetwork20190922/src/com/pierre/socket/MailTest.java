package com.pierre.socket;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import javax.sound.midi.Receiver;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

public class MailTest {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JFrame frame = new MailTestFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}

//The frame for the mail GUI
class MailTestFrame extends JFrame{
	public static final int DEFAULT_WIDTH =500;
	public static final int DEFAULT_HEIGHT = 500;
	private Scanner in;
	private PrintWriter out;
	private JTextField from;
	private JTextField to;
	private JTextField smtpServer;
	private JTextArea message;
	private JTextArea comm;
	public MailTestFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("MailTest");
		setLayout(new GridBagLayout());//GridBagLayout:网格袋布局管理器
		
		//we use the GBC convenience class of Core java volume I, chapter 9
		add(new JLabel("From:"), new GBC(0,0).setFill(GBC.HORIZONTAL));
		
		from = new JTextField(20);
		add(from, new GBC(1,0).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		
		add(new JLabel("To:"), new GBC(0,1).setFill(GBC.HORIZONTAL));
		
		to = new JTextField(20);
		add(to, new GBC(1,1).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		
		add(new JLabel("SMTP server:"), new GBC(0,2).setFill(GBC.HORIZONTAL));
		
		smtpServer = new JTextField(20);
		add(smtpServer, new GBC(1,2).setFill(GBC.HORIZONTAL).setWeight(100, 0));
		
		message = new JTextArea();
		add(new JScrollPane(message), new GBC(0,3,2,1).setFill(GBC.BOTH).setWeight(100, 100));
		
		comm = new JTextArea();
		add(new JScrollPane(comm), new GBC(0,4,2,1).setFill(GBC.BOTH).setWeight(100, 100));
		
		JPanel buttonPanel = new JPanel();
		add(buttonPanel, new GBC(0,5,2,1));
		
		JButton sendButton = new JButton("Send");
		buttonPanel.add(sendButton);
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SwingWorker<Void, Void>() {
					protected Void doInBackground() throws Exception{
						comm.setText("");
						sendMail();
						return null;
					}
				}.execute();
			}
		});
	}
	
	//sends the mail message that has been authored in the GUI
	public void sendMail() {
		try {
			Socket socket = new Socket(smtpServer.getText(), 25);
			
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			in = new Scanner(inputStream); //拿到一个网络输出的扫描器
			out = new PrintWriter(outputStream, true /*autoFluch*/);
			
			String hostName = InetAddress.getLocalHost().getHostName();
			
			receive();
			
			send("HELO" + hostName);
			receive();
			send("MAIL FROM: <" + from.getText() + ">");
			receive();
			send("RCPT TO: <" + to.getText() + ">");
			receive();
			send("DATA");
			receive();
			send(message.getText());
			send(".");
			receive();
			socket.close();
		} catch (Exception e) {
			comm.append("Error: "+e);
		}
	}
	
	// sends a string to the socket and echoes it in the comm text area
	public void send(String string) throws IOException{
		comm.append(string);
		comm.append("\n");
		out.print(string.replaceAll("\n", "\r\n"));
		out.print("\r\n");
		out.flush();
	}
	
	// receives a string from the socket and displays it in the comm text area.
	public void receive() throws IOException{
		String line = in.nextLine();
		comm.append(line);
		comm.append("\n");
	}
	
}