package controller;

import java.util.Vector;

import model.Account;
import model.Transaction;

public class AccountController {

	public static double getBalance(Vector<Transaction> transactions, String account_number) {
		double balance = 0;
		for (int i = 0; i < transactions.size(); i++) {
			Transaction aTransaction = transactions.get(i);
			if (aTransaction.getAccount_number().equals(account_number)) {
				balance += aTransaction.getAmount();
			}
		}
		return balance;
	}

	public static boolean accountExists(Vector<Account> accounts, String account_number) {
		for (int i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).getAccount_number().equals(account_number)) {
				return true;
			}
		}
		return false;
	}
}
