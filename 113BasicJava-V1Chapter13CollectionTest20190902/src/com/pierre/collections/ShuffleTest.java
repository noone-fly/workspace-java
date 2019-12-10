package com.pierre.collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ShuffleTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> number = new ArrayList<Integer>();
		for (int i = 1; i <= 49; i++) {
			number.add(i);
		}
		Collections.shuffle(number);
		List<Integer> w = number.subList(0, 6);
		Collections.sort(w);
		System.out.println(w);
		System.out.println("用户工作目录："+System.getProperty("user.dir"));
		System.out.println(System.getProperty("line.separator"));
	}
}
