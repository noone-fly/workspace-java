package com.pierre.nio;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

/**
 * this program computes the CRC checksum of a file
 * /Users/chenpiyang/Documents/workspace-java/FileIOTest/src/com/pierre/file/test.txt
 * @author chenpiyang
 *
 */
public class NIOTest {
	
	public static long checksumInputStream(String filename) throws IOException {
		InputStream inputStream = new FileInputStream(filename);
		CRC32 crc = new CRC32();
		int c;
		while ((c = inputStream.read()) != -1) {
			crc.update(c);
		}
		return crc.getValue();
	}
	
	public static long checksumBufferedInputStream(String filename) throws IOException {
		InputStream inputStream = new BufferedInputStream(new FileInputStream(filename));
		CRC32 crc = new CRC32();
		int c;
		while ((c = inputStream.read()) != -1) {
			crc.update(c);
		}
		return crc.getValue();
	}
	
	public static long checksumRandomAccessFile(String filename) throws IOException {
		RandomAccessFile file = new RandomAccessFile(filename, "r");
		long length = file.length();
		CRC32 crc = new CRC32();
		
		for (int p = 0; p < length; p++) {
			file.seek(p);
			int c = file.readByte();
			crc.update(c);
		}
		return crc.getValue();
	}
	
	public static long checksumMappedFile(String filename) throws IOException  {
		FileInputStream inputStream = new FileInputStream(filename);
		FileChannel channel = inputStream.getChannel();
		CRC32 crc32 = new  CRC32();
		int length = (int) channel.size();
		MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
		for (int p = 0; p < length; p++) {
			int c = buffer.get(p);
			crc32.update(c);
		}
		return crc32.getValue();
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Input Stream:");
		long start = System.currentTimeMillis();
		long crcValue = checksumInputStream(args[0]);
		long end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + " milliseconds");
		
		System.out.println("Buffered Input Stream:");
	    start = System.currentTimeMillis();
		crcValue = checksumBufferedInputStream(args[0]);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + " milliseconds");
		
		System.out.println("Random Access File:");
		start = System.currentTimeMillis();
		crcValue = checksumRandomAccessFile(args[0]);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + " milliseconds");
		
		System.out.println("Mapped File:");
		start = System.currentTimeMillis();
		crcValue = checksumMappedFile(args[0]);
		end = System.currentTimeMillis();
		System.out.println(Long.toHexString(crcValue));
		System.out.println((end - start) + " milliseconds");
	}

}
