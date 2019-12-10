package com.pierre.classtest;

import java.sql.Date;


public class TestClass {

	public static void main(String[] args) {
		
		//T.class 代表匹配的类对象
		System.out.println(int.class); //int
		System.out.println(Date.class);  //class java.sql.Date
		System.out.println(Double[].class); //class [Ljava.lang.Double;
		
		
		Employee employee = new Employee();
		//一个Class 对象表示一个特定类的属性
		Class class1 = employee.getClass();
		System.out.println(class1.getName() ); //com.pierre.classtest.Employee
		
		
		//调用静态方法 forName 获取类名对应的Class对象
		String className = "java.util.Date";
		try {
			Class class2 = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// newInstance() 方法可以快速创建一个类的实例，调用的是默认构造器
		String string = "java.util.Date";
		try {
			Object object = Class.forName(string).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}

}

class Employee{
	private String name;
	private int age;
}
