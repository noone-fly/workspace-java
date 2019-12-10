package io.agora.javaapi;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class TestBase64Java8 {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		final Base64.Decoder decoder = Base64.getDecoder();
		final Base64.Encoder encoder = Base64.getEncoder();
		final String text = "字符串";
		final byte[] textByte = text.getBytes("UTF-8");
		final String encodeText = encoder.encodeToString(textByte);
		System.out.println(encodeText);
		System.out.println(new String(decoder.decode(encodeText), "UTF-8"));
	}
}
