package io.pierre.annotation.button;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ActionListenerInstaller {

	/** 注解的功能是通过该方法建立起来的
	 * Processes all ActionListenerFor annotations in the given object.
	 * 把给定的对象的所有的注解信息获取到，并设置为取消Java语言访问检查
	 * @param obj
	 *            an object whose methods may have ActionListenerFor annotations
	 */
	public static void processAnnotations(Object obj) {
		try {
			//Class 类的实例表示正在运行的 Java 应用程序中的类和接口
			Class<?> class1 = obj.getClass();//返回运行时类
			
			//返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。
			for (Method method : class1.getDeclaredMethods()) {
				System.out.println("运行时类中的方法：" + method.getName() + ", ");
				//如果存在该元素的指定类型的注释，则返回这些注释
				ActionListenerFor actionListenerFor = method.getAnnotation(ActionListenerFor.class);
				if (actionListenerFor != null) {
					
					//返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。
					Field field = class1.getDeclaredField(actionListenerFor.source());
					
System.out.println("运行时类中的属性：" + field.getName() + ", ");
					
					//将反射的对象标记为在使用时取消默认 Java 语言访问控制检查的能力
					//将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。
					field.setAccessible(true);
					
					//然后添加监听
					//field.get(obj) 返回指定对象上此 Field 表示的字段的值，就是yellow, red, blue 
					addListener(field.get(obj), obj, method);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds an action listener that calls a given method
	 * @param source
	 * @param param
	 * @param method
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * source 即 yellowButtone, redButtone, blueButton 三个Button对象，
	 */
	public static void addListener(Object source, final Object param, final Method method) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		//InvocationHandler 是代理实例的调用处理程序 实现的接口
//每个代理实例都具有一个关联的调用处理程序。对代理实例调用方法时，将对方法调用进行编码并将其指派到它的调用处理程序的 invoke 方法
		InvocationHandler handler = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
				return method.invoke(param);
			}
		};

		//Proxy 提供用于创建动态代理类和实例的静态方法，它还是由这些方法创建的所有动态代理类的超类
		Object listener = Proxy.newProxyInstance(
				null, new Class[] { java.awt.event.ActionListener.class }, handler);
		Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
		
		System.out.print("listener中的属性有：");
		Field[] field = listener.getClass().getDeclaredFields();
		for (Field f : field) {
			System.out.print(f.getName() + ", ");
			
		}
		System.out.println();
		System.out.print("\n" + "listener中的方法有：");
		Method[] m = listener.getClass().getDeclaredMethods();
		for (Method m1 : m) {
			System.out.print(m1.getName() + ", ");
			
		}
		System.out.println();
		
		adder.invoke(source, listener);
	}
}
