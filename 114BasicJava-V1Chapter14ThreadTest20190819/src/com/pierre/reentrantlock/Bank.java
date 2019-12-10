package com.pierre.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

	private final double[] accounts;

	private Lock bankLock = new ReentrantLock(); // 给转账加锁
	private Condition sufficientFunds;

	// 初始化银行，100个账户，每个账户金额1000
	public Bank(int n, double initialBalance) {
		accounts = new double[n];
		for (int i = 0; i < accounts.length; i++) {
			accounts[i] = initialBalance;
		}
		sufficientFunds = bankLock.newCondition();
	}

	// 没有加锁的代码，无法确保账户总金额保持不变
	// // from 转账方， to 被转账方， amount 转账金额
	// public void transfer(int from, int to, double amount) {
	// if (accounts[from] < amount) {
	// return;
	// }
	// System.out.print(Thread.currentThread());
	// accounts[from] -= amount;
	// System.out.printf(" %10.2f from %d to %d", amount, from, to);
	// accounts[to] += amount;
	// System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
	// }

	// // 加上JSE5.0的ReentrantLock类
	// public void transfer(int from, int to, double amount) {
	// if (accounts[from] < amount) {
	// return;
	// }
	// bankLock.lock();
	// try {
	// System.out.print(Thread.currentThread());
	// accounts[from] -= amount;
	// System.out.printf(" %10.2f from %d to %d", amount, from, to);
	// accounts[to] += amount;
	// System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
	// } finally {
	// bankLock.unlock(); // make sure the lock is unlocked even if an exception is
	// thrown
	// }
	// }

	// 锁中加入条件对象
	public void transfer(int from, int to, double amount) throws InterruptedException {
		if (accounts[from] < amount) {
			return;
		}
		bankLock.lock();
		try {
			//如果转账金额大于余额，则让线程阻塞
			while (accounts[from] < amount) {
				sufficientFunds.await();
			}
			
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from, to);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
			
			//当完成转账时，调用 signalAll
			//sufficientFunds.signalAll();
			sufficientFunds.signal();
		} finally {
			bankLock.unlock(); // make sure the lock is unlocked even if an exception is thrown
		}
	}

	// 获取所有账户总金额
	// transfer 调用 getTotalBalance 方法，这也会封锁bankLock对象，此时bankLock对象的持有基计数为2
	public double getTotalBalance() {
		bankLock.lock();
		try {
			double sum = 0;
			for (double d : accounts) {
				sum += d;
			}
			return sum;
		} finally {
			bankLock.unlock();
		}
	}

	// 获取账户数量
	public int size() {
		return accounts.length;
	}
}
