package com.pierre.fileio;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class TextFileText {

	public static void main(String[] args) {
		String file = System.getProperty("user.dir") + "/src/com/pierre/fileio/employee.txt";
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tony Tester", 45000, 1990, 3, 15);

		try {
			//把雇员信息存入文件中， 文件位置是用户工作目录
			PrintWriter out = new PrintWriter(file);
			writeDate(staff, out);
			out.close();
			
			//使用扫描器从文件中检索所有的记录，并装入新的对象数组
			Scanner in = new Scanner(new FileReader(file));
			Employee[] newStaff = readData(in);
			in.close();
			
			//打印数组内容
			for (Employee employee : newStaff) {
				System.out.println(employee);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//打印对象数组长度以及数组内容
	private static void writeDate(Employee[] employees, PrintWriter out) throws IOException {
		out.println(employees.length);
		for (Employee employee : employees) {
			employee.writeData(out);
		}
	}
	
	//使用扫描器，从文件中读取内容，并返回Emplyee对象数组
	private static Employee[] readData(Scanner in) {
		int n = in.nextInt();
		in.nextLine();
		Employee[] employees = new Employee[n];
		for (int i = 0; i < n; i++) {
			employees[i] = new Employee();
			employees[i].readData(in);
		}
		return employees;
	}
}

class Employee {

	private String name;
	private double salary;
	private Date hireDay;

	public Employee() {
	}

	public Employee(String _name, double _salary, int _year, int _month, int _day) {
		name = _name;
		salary = _salary;
		GregorianCalendar calendar = new GregorianCalendar(_year, _month - 1, _day);
		hireDay = (Date) calendar.getTime(); //返回一个表示此 Calendar 时间值（从 历元至现在的毫秒偏移量）的 Date 对象。 
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void setHireDay(Date hireDay) {
		this.hireDay = hireDay;
	}

	public void raiseSalary(double byPercent) {
		double raise = byPercent / 100;
		salary += raise;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}

	//传一个 PrintWriter 对象，
	public void writeData(PrintWriter out) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(hireDay);
		out.println(name + "|" + salary + "|" + calendar.get(Calendar.YEAR) + "|"
				+ (calendar.get(Calendar.MONTH) + "|" + calendar.get(Calendar.DAY_OF_MONTH)));
	}

	//
	public void readData(Scanner in) {
		String line = in.nextLine();
		String[] tokens = line.split("\\|");
		name = tokens[0];
		salary = Double.parseDouble(tokens[1]);
		int y = Integer.parseInt(tokens[2]);
		int m = Integer.parseInt(tokens[3]);
		int d = Integer.parseInt(tokens[4]);
		//GregorianCalendar 的构造方法中的 Month 值是从 0 开始的，例如，0 表示 1 月。 所以要减1
		GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
		//返回一个表示此 Calendar 时间值（从历元至现在的毫秒偏移量）的 Date 对象。 
		hireDay = (Date) calendar.getTime();
	}
}