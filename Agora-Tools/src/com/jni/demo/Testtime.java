package com.jni.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Testtime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		System.out.print(sdf.format(date));
		int a = 001;
		System.out.print(a);
	}

}
