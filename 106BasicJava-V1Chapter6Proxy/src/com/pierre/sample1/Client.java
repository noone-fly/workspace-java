package com.pierre.sample1;

import java.lang.reflect.*;

//客户端：生成代理实例，并调用了request()方法 
public class Client {
	public static void main(String[] args) {
		Subject rs = new RealSubject();// 这里指定被代理类
		InvocationHandler ds = new DynamicSubject(rs);
		Class<?> cls = rs.getClass();

		// 以下是一次性生成代理
		//cls.getClassLoader() 定义代理类的类加载器，作为java安全模型的一部分，对于系统类和从网上下载的类，可以使用不同的类加载器，null表示使用默认的类加载器
		//cls.getInterfaces()  代理类要实现的接口列表，一个Class对象数组，每个元素都是需要实现的接口
		// ds 调用处理器，指派方法调用的调用处理程序
		Subject subject = (Subject) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), ds);

		// 这里可以通过运行结果证明subject是Proxy的一个实例，这个实例实现了Subject接口
		System.out.println(subject instanceof Proxy); //true

		// 这里可以看出subject的Class类是$Proxy0,这个$Proxy0类继承了Proxy，实现了Subject接口
		System.out.println("subject的Class类是：" + subject.getClass().toString());
		System.out.print("subject中的属性有：");

		Field[] field = subject.getClass().getDeclaredFields();
		for (Field f : field) {
			System.out.print(f.getName() + ", ");
		}

		System.out.print("\n" + "subject中的方法有：");

		Method[] method = subject.getClass().getDeclaredMethods();

		for (Method m : method) {
			System.out.print(m.getName() + ", ");
		}

		System.out.println("\n" + "subject的父类是：" + subject.getClass().getSuperclass());
		System.out.print("\n" + "subject实现的接口是：");

		Class<?>[] interfaces = subject.getClass().getInterfaces();
		for (Class<?> i : interfaces) {
			System.out.print(i.getName() + ", ");
		}

		System.out.println("\n\n" + "运行结果为：");
		subject.request();
	}
}
