package com.pierre.fileio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintWriterTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String name = "Harry Hacker";
		double salary = 100002;
		String file = System.getProperty("user.dir") + "/src/com/pierre/fileio/employee.txt";
		//PrintWriter out = new PrintWriter(file);  //三种构造方式，都可以把字符写入文件
		PrintWriter out = new PrintWriter(new FileWriter(file));
		//PrintWriter out = new PrintWriter(new FileOutputStream(file));
		out.print(name);
		out.print(" ");
		out.print(salary);
		out.print(System.getProperty("line.separator")); //输出一个回车换行，windows是 \r\n  linux 是 \n
		out.flush(); //如果不调用flush  内容就不会写入到文件中
		out.close();
 	}
}
