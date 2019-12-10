package io.agora.javaapi;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class TestBase64ApacheCommonsCodec {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		final Base64 base64 = new Base64();
		final String test = "字符串";
		final byte[] textByte = test.getBytes("utf-8");
		final String encodetext = base64.encodeAsString(textByte);
		System.out.println(test +" ==> "+ encodetext);
		
		System.out.println(new String(base64.decode(encodetext),"utf-8"));
	}

}
