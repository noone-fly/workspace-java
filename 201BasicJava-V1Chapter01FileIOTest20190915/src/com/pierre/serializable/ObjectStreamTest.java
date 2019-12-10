package com.pierre.serializable;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class ObjectStreamTest {

	public static void main(String[] args) {
		String filestr = System.getProperty("user.dir") + "/src/com/pierre/serializable/serializable.txt";
		Employee employee = new Employee("fly", 30000, 1987, 02, 22);
		Manager carl = new Manager("Carl", 90000, 1976, 12,22);
		carl.setSecretary(employee);
		
		Manager tony = new Manager("Tony", 20000, 1988, 7,2);
		tony.setSecretary(tony);
		
		Employee[] staff = new Employee[3];
		staff[0] = carl;
		staff[1] = employee;
		staff[2] = tony;
		
		try {
			//保存所有employee 记录到文件中
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filestr));
			oos.writeObject(staff);
			oos.close();
			
			// retrieve all records into a new array
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filestr));
			Employee[] newStaff = (Employee[])ois.readObject();
			ois.close();
			
			//raise secretary`s salary
			newStaff[1].raiseSalary(20);
			
			//print the newly read employee records
			for (Employee e : newStaff) {
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Employee implements Serializable{
	private String name;
	private double salary;
	private Date hireDay;
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	public Employee(String n, double d, int year, int month, int day) {
		name = n;
		salary = d;
		GregorianCalendar calendar = new GregorianCalendar(year, month -1, day);
		hireDay = (Date) calendar.getTime();
	}
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
	public Date getHireDay() {
		return hireDay;
	}
	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}
	@Override
	public String toString() {
		return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}
}

class Manager extends Employee{
	private Employee secretary;
	public Manager(String n, double s, int year, int month, int day) {
		super(n, s, year, month, day);
		secretary = null;
	}
	public Employee getSecretary() {
		return secretary;
	}
	public void setSecretary(Employee secretary) {
		this.secretary = secretary;
	}
	@Override
	public String toString() {
		return super.toString() + "[secretary=" + secretary + "]";
	}
}