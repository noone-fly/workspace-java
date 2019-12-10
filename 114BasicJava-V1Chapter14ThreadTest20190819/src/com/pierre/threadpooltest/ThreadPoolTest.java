package com.pierre.threadpooltest;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadPoolTest {
	
	private File Directory;
	private String keyword;
	private ExecutorService pool;
	private int count;

	/**
	 * 创建线程池来执行任务
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory (e.g. /Users/chenpiyang/Desktop/23276)");
		String directory = in.nextLine();
		System.out.println("Enter keyword (e.g. 6110903): ");
		String keyword = in.nextLine();
		//创建一个线程池，对应每个任务，只要有空闲线程可用，就立即执行。如果没有空闲线程，则创建新线程
		ExecutorService pool = Executors.newCachedThreadPool();
		//实现Callable接口的实例
		MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
		//调用submit 提交Runnable 或 Callable 对象。提交给线程池执行任务
		//Future对象保存异步计算的结果，用于查询该任务的状态，
		//比如：get方法获取执行结果，isDone 判断任务是否执行完毕，cancel 取消该任务
		Future<Integer> task = pool.submit(counter);
		try {
			//打印结果
			System.out.println(task.get() + " matching files.");
		} catch (ExecutionException e) {
			// TODO: handle exception
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}

/**
 * this task counts the files in a directory and its subdirecotries that contain a given keyword
 * @author chenpiyang
 *
 */
class MatchCounter implements Callable<Integer>{
	private File directory;
	private String keyword;
	private ExecutorService pool;
	private int count;
	
	public MatchCounter(File directory, String keyword, ExecutorService pool) {
		// TODO Auto-generated constructor stub
		this.directory = directory;
		this.keyword = keyword;
		this.pool= pool;
	}

	//在call 方法内部，使用相同的递归机制， 对应每个子目录， 会产生一个新的MatchCounter 并为之启动一个线程， 
	//此外，把FutureTask 对象隐藏在 ArrayList<Future<Integer>>中， 最后，把所有结果加起来
	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		count = 0;
		try {
			File[] files = directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
			for (File file : files) {
				if (file.isDirectory()) {
					MatchCounter counter = new MatchCounter(file, keyword, pool);
					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					results.add(task);
				}else {
					if (search(file)) {
						count++;
					}
				}
				for (Future<Integer> result : results) {
					try {
						count += result.get();
					} catch (ExecutionException e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * searches a file for a given keyword.
	 */
	public boolean search(File file) {
		boolean found = false;
		try {
			Scanner in = new Scanner(new FileInputStream(file));
			
			while (!found && in.hasNextLine()) {
				String line = in.nextLine();
				if (line.contains(keyword)) {
					found = true;
				}
				in.close();
			}
		} catch (Exception e) {
			return false;
		}
		return found;
	}
	
}
