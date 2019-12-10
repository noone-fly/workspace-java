package com.pierre.file;

import java.io.File;
import java.io.IOException;

public class FindDerectories {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0) {
			args = new String[] {".."};
		}
		try {
			File pathName = new File(args[0]);
			String[] fileNames = pathName.list();
			
			for (int i = 0; i < fileNames.length; i++) {
				File file1 = new File(pathName.getPath(), fileNames[i]);
				if (file1.isDirectory()) {
					System.out.println(file1.getCanonicalPath());
					main(new String[] {file1.getPath()});
				}
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
