package com.jni.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class haomiaoToDate {
	static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws InterruptedException, ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS E");
        while(true) {
            System.out.println("输入毫秒数:自动转换==>毫秒");
            long n=Long.parseLong(sc.nextLine().replaceAll("[^\\d]+", ""));
            System.out.println(sdf.format(new Date(n)));
        }
    }
}
