package com.jni.demo;

public class JNIDemo {
	
	public native void sayHello();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.loadLibrary("JNIDemo");
		JNIDemo jniDemo = new JNIDemo();
		jniDemo.sayHello();
	}

}
