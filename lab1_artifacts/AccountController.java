package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public static void loadAccountData(Vector<Account> accounts) {
		Account anAccount;
		try {
			anAccount = new Account("5495-1234", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));
			accounts.add(anAccount);

			anAccount = new Account("5495-1239", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2020"));
			accounts.add(anAccount);

			anAccount = new Account("5495-1291", "mike", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2019"));
			accounts.add(anAccount);

			anAccount = new Account("5495-6789", "David.McDonald@gmail.com", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));
			accounts.add(anAccount);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
