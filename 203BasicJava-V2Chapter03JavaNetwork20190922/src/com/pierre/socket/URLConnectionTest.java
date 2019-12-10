package com.pierre.socket;

import java.io.ByteArrayOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class URLConnectionTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String urlName;
			if (args.length > 0) {
				urlName = args[0];
			}else {
				urlName = "https://www.oracle.com/technetwork/java/javase/downloads/index.html";
			}
			URL url = new URL(urlName);
			URLConnection connection = url.openConnection();
			
			//set username, password if specified on command line
			if (args.length > 2) {
				String username = args[1];
				String password = args[2];
				String input = username + ":" + password;
				String encoding = base64Encode(input);
				connection.setRequestProperty("Authorization", "Basic " +  encoding);
			}
			
			connection.connect();
			
			//print header fields
			Map<String, List<String>> headers = connection.getHeaderFields();
			for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
				String key = entry.getKey();
				for (String value : entry.getValue()) {
					System.out.println(key + ":" + value);
				}
			}
			
			//print convenience functions
			System.out.println("----------");
			System.out.println("getContentType: " + connection.getContentType());
			System.out.println("getContentLength: " + connection.getContentLength());
			System.out.println("getcontentEncoding: " + connection.getContentEncoding());
			System.out.println("getDate: " + connection.getDate());
			System.out.println("getExpiration: " + connection.getExpiration());
			System.out.println("getLastModifed: " + connection.getLastModified());
			System.out.println("----------");
			
			Scanner in = new Scanner(connection.getInputStream());
			//print first ten lines of contents
			for (int n = 1; in.hasNextLine() && n <= 10; n++) {
				System.out.println(in.nextLine());
			}
			if (in.hasNextLine()) {
				System.out.println("...");
			}
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static String base64Encode(String string) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Base64OutputStream out = new Base64OutputStream(byteArrayOutputStream);
		try {
			out.write(string.getBytes());
			out.flush();
		} catch (IOException e) {
			// TODO: handle exception
		}
		return byteArrayOutputStream.toString();
	}

}


/** Base64编码实现 https://blog.csdn.net/jackwang_378/article/details/19479855
 * this stream filter converts a stream of bytes to their Base64 encoding
 * Base64 encoding encodes 3 bytes into 4 characters, |11111122|22223333|33444444| Each set of 6 bits is encoded according to 
 * the toBase64 map. if the number of input bytes is not a multiple of 3, then the last group of 4 characters is padded with 
 * one or two = signs. Each output line is at most 76 characters
 * 
 * @author chenpiyang
 *
 */

//Base64OutputStream 这个类继承了FilterOutputStream这个类，为什么要继承这个类呢？
//这个类其实和父类OutputStream没什么区别，只是多提供了几个write方法和一个out字段而已。
//这个字段很重要，因为Base64OutputStream这个类的构造函数中要用到他。我的理解这有点像装饰模式，包装了一下，加了一些功能而已。
class Base64OutputStream extends FilterOutputStream{
	
	//这就是base64的编码表，如果我们要定义自己的编码表就可以通过更改这个表中的内容。比如用中文，那么到时候编码之后就全部是中文了。
	private static char[] toBase64 = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
	        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
	        'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
	        'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
	
	private int col = 0;
	private int i = 0;
	private int[] inbuf = new int[3];
	
	//构造函数，编码之后的内容放到这个OutputStream中； 
	public Base64OutputStream(OutputStream out) {
		// TODO Auto-generated constructor stub
		super(out);
	}
	
	//这就是Base64编码的关键代码了。把三个字符变成四个字符。每6个字节为一组，前边补2个0.那些位操作就是做这件事情的。
	public void write(int c) throws IOException {
		inbuf[i] = c;
		i++;
		if (i == 3) {
			super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
			super.write(toBase64[((inbuf[0] & 0x03) << 4) | ((inbuf[1] & 0xF0) >> 4)]);
			super.write(toBase64[((inbuf[1] & 0x0F) << 2) | ((inbuf[2] & 0xC0) >> 6)]);
			super.write(toBase64[inbuf[2] & 0x3F]);
			col += 4;
			i = 0;
			if (col >= 76) {
				super.write('\n');
				col = 0;
			}
		}
	}
	
	//最后还有一点是如果最后剩下的字节不足三个怎么办？这就是flush方法了
	//如果只剩下一个字符，那么补两个 ‘=’号，如果剩下一个字符，补一个'='号
	public void flush() throws IOException{
		if (i == 1) {
			super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
			super.write(toBase64[(inbuf[0] & 0x03) << 4]);
			super.write('=');
			super.write('=');
		}else if (i == 2) {
			super.write(toBase64[(inbuf[0] & 0xFC) >> 2]);
			super.write(toBase64[((inbuf[0] & 0x03) << 4) | ((inbuf[1] & 0xF0) >> 4)]);
			super.write(toBase64[(inbuf[0] & 0x0F) << 2]);
			super.write('=');
		}
		
		if (col > 0) {
			super.write('\n');
			col = 0;
		}
	}
}
