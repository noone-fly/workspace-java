package com.pierre.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 服务端和客户端的交互
 * @author chenpiyang
 *
 */
public class EchoServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket = new ServerSocket(8189); //监听8189端口
			Socket incoming = serverSocket.accept(); //监听客户端请求
			try {
				InputStream inStream = incoming.getInputStream(); //获取客户端请求过来的输入流，从网络连接读，输入到
				OutputStream outStream = incoming.getOutputStream(); //获取server端的输出流
 
				Scanner in = new Scanner(inStream); // 在server端的输入流上构建一个扫描器，
				PrintWriter out = new PrintWriter(outStream, true /* auto flash */); // 在server的输出流上构建一个写入器，写入到网络连接

				out.println("Hello, Enter bye to exit"); 

				boolean done = false;
				while (!done && in.hasNextLine()) {
					String line = in.nextLine(); // 从扫描器中一行行获取网络连接的内容
					out.println("Echo: " + line); // 简便处理，然后把行内容再写入到网络连接，正常情况是服务端根据客户端的请求，作出响应处理结果
					if (line.trim().equals("bye")) { //如果客户端发送流bye 则服务端结束响应
						done = true;
					}else {
						out.println("Please enter the bye");
					}
				}
			} finally {
				incoming.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
