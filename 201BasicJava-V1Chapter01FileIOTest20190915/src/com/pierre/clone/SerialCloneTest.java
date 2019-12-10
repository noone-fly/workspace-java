package com.pierre.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class SerialCloneTest {

	public static void main(String[] args) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Employee harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
		//clone harry
		Employee harry2 = (Employee)harry.clone();
		
		//mutate harry
		harry.raiseSalary(30);
		//now harry and the clone are different
		System.out.println(harry);
		System.out.println(harry2);
	}

}

class SerialCloneable implements Cloneable, Serializable{
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		try {
			// save the object to a byte array
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);
			objectOutputStream.close();
			
			// read a clone of the object from the byte array
			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			Object object = objectInputStream.readObject();
			objectInputStream.close();
			return object;
		} catch (Exception e) {
			return null;
		}
	}
}

class Employee extends SerialCloneable{
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