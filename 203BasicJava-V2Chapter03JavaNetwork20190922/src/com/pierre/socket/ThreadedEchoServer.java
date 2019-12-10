package com.pierre.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ThreadedEchoServer {
	public static void main(String[] args) throws IOException {
		try {
			int i = 1;
			ServerSocket serverSocket = new ServerSocket(8189);
			while (true) {
				Socket incoming = serverSocket.accept();
				System.out.println("spawning " + i);
				Runnable runnable = new ThreadedEchoHandler(incoming);
				Thread thread = new Thread(runnable);
				thread.start();
				i++;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}

class ThreadedEchoHandler implements Runnable{
	private Socket incoming;
	public ThreadedEchoHandler(Socket i) {
		incoming = i;
	}

	@Override
	public void run() {
		try {
			try {
				//获取客户端请求过来的输入流，从网络连接读，输入到
				InputStream inStream = incoming.getInputStream(); 
				//获取server端的输出流
				OutputStream outStream = incoming.getOutputStream(); 
				 // 在server端的输入流上构建一个扫描器，
				Scanner in = new Scanner(inStream);
				 // 在server的输出流上构建一个写入器，写入到网络连接
				PrintWriter out = new PrintWriter(outStream, true /* auto flash */);

				out.println("Hello, Enter bye to exit"); 

				boolean done = false;
				while (!done && in.hasNextLine()) {
					// 从扫描器中一行行获取网络连接的内容
					String line = in.nextLine(); 
					// 简便处理，然后把行内容再写入到网络连接，正常情况是服务端根据客户端的请求，作出响应处理结果
					out.println("Echo: " + line);
					//如果客户端发送流bye 则服务端结束响应
					if (line.trim().equals("bye")) { 
						done = true;
					}else {
						out.println("Please enter the bye");
					}
				}
			} finally {
				incoming.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}