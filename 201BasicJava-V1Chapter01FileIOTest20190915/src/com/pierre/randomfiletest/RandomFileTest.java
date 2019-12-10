package com.pierre.randomfiletest;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RandomFileTest {

	public static void main(String[] args) {
		String filestr = System.getProperty("user.dir") + "/src/com/pierre/randomfiletest/randomfile.txt";
		File file = new File(filestr);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Employee[] staff = new Employee[3];
		staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
		staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
		staff[2] = new Employee("Tony Tester", 45000, 1990, 3, 15);
		
		try {
			//save all employee records to the file randomfile.txt
			//DataInput 接口用于从二进制流中读取字节
			//DataOutputStream 数据输出流允许应用程序以适当方式将基本 Java 数据类型写入输出流中
			DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
			for (Employee employee : staff) {
				employee.writeData(outputStream);
			}
			outputStream.close();
			
			//检索retrieve all record into a new array
			//r 用于读入访问
			RandomAccessFile in = new RandomAccessFile(file, "r");
			//compute the array size
			int n = (int)(in.length() / Employee.RECORD_SIZE);
			Employee[] newStaff = new Employee[n];
			
			// read employees in reverse order
			for (int i = n - 1; i >= 0; i--) {
				newStaff[i] = new Employee();
				//seek的参数值位于0到文件按照字节长度度量的长度之间
				in.seek(i * Employee.RECORD_SIZE);
				newStaff[i].readData(in);
			}
			in.close();
			
			//print the newly read employee records
			for (Employee employee : newStaff) {
				System.out.println(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


class Employee {
	// 名字的长度设定为40字符 = 80字节
	// salary 的长度设置为 1 double = 8字节
	// 日期使用三个int = 12 字节
	public static final int NAME_SIZE = 40;
	public static final int RECORD_SIZE = 2 * NAME_SIZE + 8 + 4 + 4 + 4;
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
	public double getSalary() {
		return salary;
	}
	public Date getHireDay() {
		return hireDay;
	}
	public void raiseSalary(double byPercent) {
		double raise = byPercent / 100;
		salary += raise;
	}
	@Override
	public String toString() {
		return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}

	//Writes employee data to a data output
	//这里传DataOutput接口类型的形参，
	public void writeData(DataOutput out) throws IOException {
		DataIO.writeFixedString(name, NAME_SIZE, out); //将字符串按照char输入到输出流
		out.writeDouble(salary);
		
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(hireDay);
		out.writeInt(calendar.get(Calendar.YEAR));
		out.writeInt(calendar.get(Calendar.MONTH) + 1);
		out.writeInt(calendar.get(Calendar.DAY_OF_MONTH));
	}

	// Reads employee data from a data input
	public void readData(DataInput in) throws IOException {
		name = DataIO.readFixedString(NAME_SIZE, in);
		salary = in.readDouble();
		int y = in.readInt();
		int m = in.readInt();
		int d = in.readInt();
		//GregorianCalendar 的构造方法中的 Month 值是从 0 开始的，例如，0 表示 1 月。 所以要减1
		GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
		//返回一个表示此 Calendar 时间值（从历元至现在的毫秒偏移量）的 Date 对象。 
		hireDay = (Date) calendar.getTime();
	}
}

class DataIO{
	public static String readFixedString(int size, DataInput in) throws IOException {
		StringBuilder builder = new StringBuilder(size);
		int i = 0;
		boolean more = true;
		while (more && i < size) {
			char ch = in.readChar();
			i++;
			if (ch == 0) {
				more = false;
			}else {
				builder.append(ch);
			}
		}
		in.skipBytes(2 * (size - i));
		return builder.toString();
	}
	public static void writeFixedString(String string, int size, DataOutput out) throws IOException {
		for (int i = 0; i < size; i++) {
			char ch = 0;
			if (i < string.length()) {
				ch = string.charAt(i); //返回指定索引处的 char 值
				out.writeChar(ch);//将一个 char 值写入输出流，该值由两个字节组成。两个字节16个bit位。
			}
		}
	}
}