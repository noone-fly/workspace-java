package com.pierre.file;

import java.io.File;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filestr = System.getProperty("user.dir") + "/src/com/pierre/file/test.txt";
		
		File file = new File(filestr);
		if ( !file.exists()) {
			file.createNewFile();
		}
		
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
		System.out.println(File.separator);
		
		if (file.isAbsolute()) {
			System.out.println("isAbsolute");
		}
		if (file.isDirectory()) {
			System.out.println("isDerectory");
		}
		if (file.isFile()) {
			System.out.println("isFile");
		}
		
	}

}
