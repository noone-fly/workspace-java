package com.pierre.tree;

import java.util.Collections;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {
		// 按照部件编号排序，自动生序，
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("Toaster", 1234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 9912));
		System.out.println(parts);
		
		//使用定制的比较器来按照描述信息排序
		SortedSet<Item> sortByDescription = new TreeSet<Item>(new Comparator<Item>() {
			@Override
			public int compare(Item a, Item b) {
				String descrA = a.getDescription();
				String descrB = b.getDescription();
				return descrA.compareTo(descrB);
			}
		});
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}
}

class Item implements Comparable<Item>{
	private String description;
	private int partNumber;
	
	public Item(String _desciption, int _partNumber) {
		description = _desciption;
		partNumber = _partNumber;
	}
	
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return "[description="+ description + ", partNumber="+partNumber+"] \n";
	}
	
	@Override
	public int hashCode() {
		return 13 * description.hashCode() + 17 * partNumber;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Item other = (Item)obj;
		return description.equals(other.description) && partNumber == other.partNumber;
	}

	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return partNumber - o.partNumber;
	}
}