package fly.io.com.agora;

import java.util.zip.CRC32;

public class TestCRC {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CRC32 crc32 = new CRC32();
		crc32.update("147258".getBytes());
		System.out.println((int)crc32.getValue());
		System.out.println(crc32.getValue());
		System.out.println(Integer.toBinaryString((int)crc32.getValue()));
		System.out.println(Integer.parseInt("11101",2));
		//System.out.println(Integer.parseInt("11101010011001011110000000100011",2));
		//System.out.println(Integer.parseInt( String.valueOf(Integer.toBinaryString((int)crc32.getValue())) ));
		System.out.println(Long.toBinaryString(crc32.getValue()));
	}

}
