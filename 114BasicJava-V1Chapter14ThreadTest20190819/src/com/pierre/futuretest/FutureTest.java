package com.pierre.futuretest;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javax.naming.directory.SearchControls;

public class FutureTest {
	//这个程序与前面那个寻找包含指定关键字的文件的例子相似，但是这个例子我们只计算匹配的文件数量，
	//因此，程序有一个需要长时间运行的任务，它产生一个整数值， 一个 Callable<Integer> 的例子
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter base directory (e.g. /Users/chenpiyang/Documents/GithubProject)");
		String directory = in.nextLine();
		System.out.println("Enter keyword (e.g. volatile): ");
		String keyword = in.nextLine();
		//利用MatchCounter 创建一个 FutureTask 对象，并用来启动一个线程
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		Thread thread = new Thread(task);
		thread.start();
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

class MatchCounter implements Callable<Integer>{
	private File directory;
	private String keyword;
	private int count;
	
	public MatchCounter(File directory, String keyword) {
		// TODO Auto-generated constructor stub
		this.directory = directory;
		this.keyword = keyword;
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
					MatchCounter counter = new MatchCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					results.add(task);
					Thread thread = new Thread(task);
					thread.start();
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
