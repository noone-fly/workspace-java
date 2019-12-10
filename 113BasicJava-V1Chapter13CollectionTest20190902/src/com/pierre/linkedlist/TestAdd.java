package com.pierre.linkedlist;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class TestAdd {
	public static void main(String[] args) {
		List<String> b = new LinkedList<String>();
		b.add("1");
		b.add("2");
		b.add("3");
		b.add("4");
		System.out.println(b);
		
		ListIterator<String> listIterator  = b.listIterator(3);
		//listIterator.previous();
		listIterator.add("0");
		System.out.println(b);
	}
}
