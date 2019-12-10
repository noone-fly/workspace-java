package com.pierre.map;
import java.util.HashMap;
import java.util.Map;


public class MapTest {
	public static void main(String[] args) {
		Map<String, Employee> staff = new HashMap<String, Employee>();
		staff.put("144-25-5464", new Employee("amy lee"));
		staff.put("567-24-2546", new Employee("harry hacker"));
		staff.put("157-62-7935", new Employee("gary cooper"));
		staff.put("456-62-5527", new Employee("Francesca Crux"));
		//打印所有
		System.out.println("print all entries  -->");
		System.out.println(staff);
		
		staff.remove("567-24-2546");
		
		staff.put("456-62-5527", new Employee("Francesca Miller"));
		System.out.println("get one entry -->");
		System.out.println(staff.get("157-62-7935"));
		System.out.println("print the key/value  -->");
		for (Map.Entry<String, Employee> entry : staff.entrySet()) {
			String key = entry.getKey();
			Employee value = entry.getValue();
			System.out.println("key="+key+", value="+value);
		}
	}
}

class Employee{
	private String name;
	private double salary;
	public Employee(String s) {
		name = s;
		salary = 0;
	}
	@Override
	public String toString() {
		return "[name=" + name + ", salary=" + salary + "]\n";
	}
}