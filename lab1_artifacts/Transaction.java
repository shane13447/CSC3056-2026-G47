package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
	private String account_number;
	private double amount;
	private Date transaction_date;

	public Transaction(String account_number, double amount, Date transaction_date) {
		this.account_number = account_number;
		this.amount = amount;
		this.transaction_date = transaction_date;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getTransaction_date() {
		return transaction_date;
	}

	public void setTransaction_date(Date transaction_date) {
		this.transaction_date = transaction_date;
	}

	public String getAccountNumber() {
		return getAccount_number();
	}

	public void setAccountNumber(String accountNumber) {
		setAccount_number(accountNumber);
	}

	public double getTransactionAmount() {
		return getAmount();
	}

	public void setTransactionAmount(double transactionAmount) {
		setAmount(transactionAmount);
	}

	public Date getTransactionDate() {
		return getTransaction_date();
	}

	public void setTransactionDate(Date transactionDate) {
		setTransaction_date(transactionDate);
	}

	@Override
	public String toString() {
		String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(transaction_date);
		return String.format("%-10s| %-10.2f| %-15s", account_number, amount, formattedDate);
	}
}
