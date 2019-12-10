package com.pierre.unsynch;

public class UnsynchBank {
	
	public static final int NACCOUNTS = 100;//100个银行账户
	public static final double INITIAL_BALANCE = 1000;//每个账户初始金额为1000

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		int i;
		for ( i = 0; i < NACCOUNTS; i++) {
			TransferRunnable runnable = new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread thread = new Thread(runnable);
			thread.start();
		}
	}
}
