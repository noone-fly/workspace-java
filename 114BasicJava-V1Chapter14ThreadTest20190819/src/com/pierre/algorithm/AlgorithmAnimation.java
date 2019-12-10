package com.pierre.algorithm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This program animates a sort algorithm
 * @author chenpiyang
 *
 */

public class AlgorithmAnimation {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				//创建
				JFrame jFrame = new AnimationFrame();
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				jFrame.setVisible(true);
			}
		});
	}
}

/**
 * this frame shows the array as it is sorted, together with buttons to single-step the animation or to run it without interruption
 */
class AnimationFrame extends JFrame{
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
	public AnimationFrame() {
		// TODO Auto-generated constructor stub
		ArrayComponent component = new ArrayComponent();
		add(component, BorderLayout.CENTER);
		final Sorter sorter = new Sorter(component);
		
		JButton runButton = new JButton("run");
		runButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sorter.setRun();
			}
		});
		
		JButton stepButton = new JButton("step");
		runButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sorter.setRun();
			}
		});
		
		JPanel buttons = new JPanel();
		buttons.add(runButton);
		buttons.add(stepButton);
		add(buttons, BorderLayout.NORTH);
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		Thread thread = new Thread(sorter);
		thread.start();
	}
}

/**
 * this runnable executes a sort algorithm, when two elements are compared, the algorithm pauses and updates a component.
 */
class Sorter implements Runnable{
	private Double[] values;
	private ArrayComponent component;
	//一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。
	//每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。 
	private Semaphore gate;
	private static final int DELAY = 100;
	//同步域
	private volatile boolean run;
	//double数组初始长度
	private static final int VALUES_LENGTH = 30;
	//constructs a sorter
	public Sorter(ArrayComponent comp) {
		//初始化double数组，长度30
		values = new Double[VALUES_LENGTH];
		//给数组赋随机值
		for (int i = 0; i < values.length; i++) {
			values[i] = new Double(Math.random());
		}
		this.component = comp;
		this.gate = new Semaphore(1);
		this.run = false;
	}
	
	//sets the sorter to run code, called on the event dispatch thread
	public void setRun() {
		run = true;
		// release() 添加一个许可，从而可能释放一个正在阻塞的获取者
		gate.release();
	}
	
	//sets the sorter to step mode, called on the event dispatch tread.
	public void setStep() {
		run = false;
		gate.release();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Comparator<Double> comp = new Comparator<Double>() {

			//compare(T o1, T o2) 比较用来排序的两个参数。根据第一个参数小于、等于或大于第二个参数分别返回负整数、零或正整数。 
			@Override
			public int compare(Double o1, Double o2) {
				// TODO Auto-generated method stub
				component.setValues(values, o1, o2);
				try {
					if (run) {
						Thread.sleep(DELAY);
					}else {
						//获取一个许可（如果提供了一个）并立即返回，将可用的许可数减 1。
						gate.acquire();
					}
				} catch (InterruptedException e) {
					// TODO: handle exception
					Thread.currentThread().interrupt();
				}
				return o1.compareTo(o2);
			}
		};
		//根据指定比较器产生的顺序对指定对象数组进行排序
		//values  要排序的数组 
		//comparable  确定数组顺序的比较器，null 值指示应该使用元素的 自然顺序
		Arrays.sort(values, comp);
		component.setValues(values, null, null);
	}
}

/**
 * this camponent draws an array and marks two elements in the array
 */
class ArrayComponent extends JComponent{

	private static final long serialVersionUID = 1L;
	//
	private Double marked1;
	//
	private Double marked2;
	private Double[] values;
	
	//sets the values to be painted, called on the sorter thread.
	public synchronized void setValues(Double[] values, Double marked1, Double marked2) {
		this.values = values.clone();
		this.marked1 = marked1;
		this.marked2 = marked2;
		//绘制一个组件，需要定义一个扩展JComponent的类，并覆盖其中的 paintComponent(Graphics g) 方法，
		//在JAVA中，所有的绘制都必须使用Craphics对象。只要窗口需要重新绘图时，事件处理器就会通告组件，从而执行所有组件的paintComponent方法。
		//不用自己调用paintComponent方法，在应用程序需要重新绘图的时候，这个方法会自动的被调用，
		//如果需要强制重新绘制组件，那么要调用的是repaint方法，他将引发采用相应配置的Graphics对象调用所有组件的paintComponent方法。 
		repaint();
	}
	
	//called on the event dispatch thread
	public synchronized void paintComponent(Graphics g) {
		if (values == null) {
			return;
		}
		Graphics2D g2 = (Graphics2D) g;
		//
		int width = getWidth() / values.length;
		for (int i = 0; i < values.length; i++) {
			//
			double height = values[i] + getHeight();
			Rectangle2D rectangle2d = new Rectangle2D.Double(width * i, 0, width, height);
			if (values[i] == marked1 || values[i] == marked2) {
				g2.fill(rectangle2d);
			}else {
				g2.draw(rectangle2d);
			}
		}
	}
}




