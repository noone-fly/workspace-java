package com.pierre.linkedlist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 简单创建两个链表，并将它们合并起来，然后从第二个链表中每间隔一个元素删除一个元素，最好测试removeAll方法
 * @author chenpiyang
 *
 */
public class LinkedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> a = new LinkedList<String>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");
		
		List<String> b = new LinkedList<String>();
		b.add("Bob");
		b.add("Doug");
		b.add("Frances");
		b.add("Gloria");
		
		//merge the words from b into a
		ListIterator<String> alistIterator = a.listIterator();
		Iterator<String> bIterator = b.iterator();
		
		while (bIterator.hasNext()) {
			if (alistIterator.hasNext()) {
				alistIterator.next();
			}
			alistIterator.add(bIterator.next());;
		}
		System.out.println(a);
		
		//remove every second word from b
		bIterator = b.iterator();
		while (bIterator.hasNext()) {
			bIterator.next(); // skip one element
			if (bIterator.hasNext()) {
				bIterator.next(); // skip next element
				bIterator.remove();  //remove that element
			}
		}
		
		System.out.println(b);
		
		//bulk operation: remove all words in b from a
		a.removeAll(b);
		System.out.println(a);
	}

}
