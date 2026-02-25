package utils;

import model.Transaction;
import java.util.Date;
import java.text.SimpleDateFormat;
import utils.TestUtils;

class TransactionTest {

    public static void main(String[] args) {
        testConstructor();
        testSetters();
        testToString();
    }

    private static void testConstructor() {
        String testName = "Constructor Test";

        String accountNumber = "ACC12345";
        double amount = 250.75;
        Date date = new Date();

        Transaction transaction = new Transaction(accountNumber, amount, date);

        boolean accountCorrect = transaction.getAccount_number().equals(accountNumber);
        boolean amountCorrect = transaction.getAmount() == amount;
        boolean dateCorrect = transaction.getTransaction_date().equals(date);

        if (accountCorrect && amountCorrect && dateCorrect) {
            TestUtils.printTestPassed(testName);
        } else {
            TestUtils.printTestFailed(testName);
        }
    }

    private static void testSetters() {
        String testName = "Setter Methods Test";

        Transaction transaction = new Transaction("OLD123", 100.00, new Date());

        String newAccountNumber = "NEW98765";
        double newAmount = 500.50;
        Date newDate = new Date(System.currentTimeMillis() + 100000);

        transaction.setAccount_number(newAccountNumber);
        transaction.setAmount(newAmount);
        transaction.setTransaction_date(newDate);

        boolean accountCorrect = transaction.getAccount_number().equals(newAccountNumber);
        boolean amountCorrect = transaction.getAmount() == newAmount;
        boolean dateCorrect = transaction.getTransaction_date().equals(newDate);

        if (accountCorrect && amountCorrect && dateCorrect) {
            TestUtils.printTestPassed(testName);
        } else {
            TestUtils.printTestFailed(testName);
        }
    }

    private static void testToString() {
        String testName = "toString Method Test";

        String accountNumber = "ACC111";
        double amount = 123.45;
        Date date = new Date(0); // 01/01/1970 for predictable formatting

        Transaction transaction = new Transaction(accountNumber, amount, date);

        String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
        String expectedString = String.format("%-10s| %-10.2f| %-15s",
                accountNumber, amount, formattedDate);

        String actualString = transaction.toString();

        if (actualString.equals(expectedString)) {
            TestUtils.printTestPassed(testName);
        } else {
            TestUtils.printTestFailed(testName);
            System.out.println("Expected: " + expectedString);
            System.out.println("Actual:   " + actualString);
        }
    }
}