package com.pierre.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			//建立一个套接字
			//Socket socket = new Socket("time-A.timefreq.bldrdoc.gov", 13);
			//Socket socket = new Socket("localhost", 8189);
			Socket socket = new Socket("127.0.0.1", 8189);
			try {
				//从网络连接读取数据到jvm
				InputStream inputStream = socket.getInputStream();
				Scanner in = new Scanner(inputStream);
				while (in.hasNextLine()) {
					String line = in.nextLine();
					System.out.println(line);
				}
			} finally {
				socket.close();
			} 
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
