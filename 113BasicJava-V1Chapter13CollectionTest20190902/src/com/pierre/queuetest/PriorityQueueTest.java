package com.pierre.queuetest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityQueue<GregorianCalendar> pGregorianCalendars = new PriorityQueue<GregorianCalendar>();
		pGregorianCalendars.add(new GregorianCalendar(1906, Calendar.DECEMBER, 9)); //G. Hopper
		pGregorianCalendars.add(new GregorianCalendar(1815, Calendar.DECEMBER, 10)); //A. Lovelace
		pGregorianCalendars.add(new GregorianCalendar(1903, Calendar.DECEMBER, 3)); //J. von Neumann
		pGregorianCalendars.add(new GregorianCalendar(1910, Calendar.JUNE, 22)); //K. Zuse
		System.out.println("Iterating over elements...");
		for (GregorianCalendar date : pGregorianCalendars) {
			System.out.println(date.get(Calendar.YEAR));
		}
		System.out.println("Removing elements...");
		while (!pGregorianCalendars.isEmpty()) {
			System.out.println(pGregorianCalendars.remove().get(Calendar.YEAR));
		}
	}
}
