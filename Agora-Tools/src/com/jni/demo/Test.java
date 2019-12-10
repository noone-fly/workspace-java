package com.jni.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");//24小时制
		System.out.println(System.currentTimeMillis()/1000 + 1200);
		System.out.println((int)(new Date().getTime()/1000));
		
		long times= 1530266390;
		Date date2 = new Date();
		date2.setTime(times);
		//System.out.println(simpleDateFormat.format(date2));
		
		Date d = new Date(System.currentTimeMillis()+1531291928);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		System.out.println(sdf.format(d));
	}

}
