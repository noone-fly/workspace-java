package com.pierre.set;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 利用Set集合元素不重复的特性，统计一篇文章的单词个数
 * 利用扫描器 Scanner 读取单词
 * @author chenpiyang
 * 20190910
 */
public class SetTest {

	public static void main(String[] args) throws IOException {
		// http://www.gutenberg.net
		// /Users/chenpiyang/Documents/workspace-java/CollectionTest/src/com/pierre/set/alice30.txt
		Set<String> words = new HashSet<String>(); // hashset implements set
		long totalTime = 0;
		Scanner in = new Scanner(Paths.get("/Users/chenpiyang/Documents/workspace-java/CollectionTest/src/com/pierre/set/alice30.txt"));
		in.useDelimiter(" ");//设置分隔符
		while (in.hasNext()) {
			String word = in.next();
			long callTime = System.currentTimeMillis();
			words.add(word);
			callTime = System.currentTimeMillis() - callTime;
			totalTime += callTime;
		}
		Iterator<String> iterator = words.iterator();
		for (int i = 1; i <= 20; i++) {
			System.out.println(iterator.next());
		}
		System.out.println("...");
		System.out.println(words.size() + " distinct wrods. " + totalTime + " milliseconds ");
	}
}
