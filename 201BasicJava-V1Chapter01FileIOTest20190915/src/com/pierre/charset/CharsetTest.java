package com.pierre.charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;

public class CharsetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Charset charset = Charset.forName("UTF-8");
		Map<String, Charset> charsets = Charset.availableCharsets();
		for(String name : charsets.keySet()){
		   //System.out.println(name);
		}
		
		String str = "不想干了";
		ByteBuffer buffer = charset.encode(str);
		byte[] bytes = buffer.array();
		System.out.println(bytes.length);
		
		ByteBuffer bbuf = ByteBuffer.wrap(bytes, 4, 15);
		CharBuffer cbuf = charset.decode(bbuf);
		String str1 = cbuf.toString();
		System.out.println(str1);
		
		
	}
}
