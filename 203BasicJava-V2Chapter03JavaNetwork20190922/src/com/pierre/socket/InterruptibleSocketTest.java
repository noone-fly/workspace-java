package com.pierre.socket;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InterruptibleSocketTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				JFrame jFrame = new InterruptibleSocketFrame();
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jFrame.setVisible(true);
			}
		});
	}

}

class InterruptibleSocketFrame extends JFrame{
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	private Scanner in;
	private JButton interruptibleButton; //可中断的按钮
	private JButton blockingButton;  //阻塞的按钮
	private JButton cancelButton;  
	private JTextArea messages;
	private TestServer server;
	private Thread connectThread;
	
	public InterruptibleSocketFrame() {
		setSize(WIDTH, HEIGHT); //设置框架宽高
		setTitle("InterruptibleSocketTest");//设置框架标题
		JPanel northPanel = new JPanel(); //创建组建
		add(northPanel, BorderLayout.NORTH); //添加到框架中，并设置位置
		
		messages = new JTextArea(); //创建文本框
		add(new JScrollPane(messages)); //添加文本到组建
		
		interruptibleButton = new JButton("Interruptible"); //创建可中断按钮
		blockingButton = new JButton("Blocking"); 
		
		northPanel.add(interruptibleButton); // 添加按钮到组建
		northPanel.add(blockingButton);
		
		//添加按钮的监听事件
		interruptibleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				interruptibleButton.setEnabled(false); //置灰
				blockingButton.setEnabled(false); //置灰
				cancelButton.setEnabled(true); //可选
				connectThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							connectInterruptibly();
						} catch (IOException e2) {
							messages.append("\nInterruptibleSocketText.connectInterruptily: "+e);
						}
					}
				});
				connectThread.start();
			}
		});
		
		blockingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				interruptibleButton.setEnabled(false); //置灰
				blockingButton.setEnabled(false); //置灰
				cancelButton.setEnabled(true); //可选
				connectThread = new Thread(new Runnable() {
					
					@Override
					public void run() {
						try {
							connectBlocding();
						} catch (IOException e2) {
							messages.append("\nInterruptibleScoketTest.connectBlocking: "+e);
						}
					}
				});
				connectThread.start();
			}
		});
		
		cancelButton = new JButton("Cancel");
		cancelButton.setEnabled(false);
		northPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				connectThread.interrupt();
				cancelButton.setEnabled(false);
			}
		});
		server = new TestServer();
		new Thread(server).start();
	}
	
	//Connects to the test server, using interruptible I/O
	//
	public void connectInterruptibly() throws IOException{
		messages.append("Interruptible: \n");
		SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8189));
		try {
			in = new Scanner(channel);
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if (in.hasNextLine()) {
					String line = in.nextLine();
					System.out.println(line);
					messages.append(line);
					messages.append("\n");
				}
			}
		} finally {
			channel.close();
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					messages.append("Channel closed\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}
			});
		}
	}
	
	//Connects to the test server, using blocking I/O
	
	public void connectBlocding() throws IOException {
		messages.append("Blocking: \n");
		Socket socket = new Socket("127.0.0.1", 8189);
		try {
			in = new Scanner(socket.getInputStream());
			while (!Thread.currentThread().isInterrupted()) {
				messages.append("Reading ");
				if (in.hasNextLine()) {
					String line = in.nextLine();
					messages.append(line);
					messages.append("\n");
				}
			}
		} finally {
			socket.close();
			EventQueue.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					messages.append("Socket close\n");
					interruptibleButton.setEnabled(true);
					blockingButton.setEnabled(true);
				}
			});
		}
	}
	
	//A multithreaded server that listens to port 8189 and sends numbers to the client.
	//simulating a hanging server after 10 numbers
	//服务器连续发送数字，并在每次发送十个数字之后滞留一下，点击两个按钮中的任何一个，都会启动一个线程来连接服务器并打印输出，
	//第一个线程使用可中断套接字
	class TestServer implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				ServerSocket serverSocket = new ServerSocket(8189);
				while (true) {
					Socket incoming = serverSocket.accept();
					Runnable runnable = new TestServerHandler(incoming);
					Thread thread = new Thread();
					thread.start();
				}
			} catch (IOException e) {
				// TODO: handle exception
				messages.append("\nTestServer.run"+e);
			}
		}
	}
	
	//This class handles the client input for one server socket connection
	//传入一个 server socket , 起用多线程来操作
	class TestServerHandler implements Runnable{
		private Socket incoming;
		private int counter;
		public TestServerHandler(Socket i) {
			incoming = i;
		}
		@Override
		public void run() {
			try {
				//获取server端的输出流
				OutputStream outputStream = incoming.getOutputStream();
				// 在server的输出流上构建一个写入器，写入到网络连接
				PrintWriter out = new PrintWriter(outputStream, true /*autoFluch*/);
				while (counter < 100) {
					counter++;
					if (counter <= 10 ) {
						out.println(counter);
						Thread.sleep(100);
					}
					incoming.close();
					messages.append("Closing server\n");
				}
			} catch (Exception e) {
				messages.append("\nTestServerHandler.run:"+e);
			}
		}
	}
	
}


