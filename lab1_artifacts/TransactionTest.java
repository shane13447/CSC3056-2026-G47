package tests;

import model.Transaction;
import utils.TestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Unit test class for the Transaction model (TODO7).
 * Written manually, not AI-generated.
 * One test method per method under test, using if-else verification.
 */
public class TransactionTest {

    public static void testTransactionConstructor() {
        // 1-Setup
        String test_account_number = "5495-1234";
        double test_amount = 50.21;
        Date test_date;
        try {
            test_date = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        // 2-Exercise
        Transaction testTransaction = new Transaction(test_account_number, test_amount, test_date);

        // 3-Verify
        System.out.println("Starting the assertions of the test method: testTransactionConstructor");

        if (testTransaction.getAccount_number() == test_account_number)
            TestUtils.printTestPassed("TC1-getAccount_number");
        else
            TestUtils.printTestFailed("TC1-getAccount_number");

        if (testTransaction.getTransactionAmount() == test_amount)
            TestUtils.printTestPassed("TC2-getTransaction_amount");
        else
            TestUtils.printTestFailed("TC2-getTransaction_amount");

        if (testTransaction.getTransaction_date().equals(test_date))
            TestUtils.printTestPassed("TC3-getTransaction_date");
        else
            TestUtils.printTestFailed("TC3-getTransaction_date");

        // 4-Teardown: none
    }

    public static void testTransactionSetters() {
        // 1-Setup
        Date originalDate;
        Date newDate;
        try {
            originalDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
            newDate = new SimpleDateFormat("dd/MM/yyyy").parse("25/12/2021");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        Transaction testTransaction = new Transaction("0000-0000", 100.00, originalDate);

        String new_account_number = "9999-8888";
        double new_amount = -25.50;

        // 2-Exercise
        testTransaction.setAccount_number(new_account_number);
        testTransaction.setTransactionAmount(new_amount);
        testTransaction.setTransaction_date(newDate);

        // 3-Verify
        System.out.println("\nStarting the assertions of the test method: testTransactionSetters");

        if (testTransaction.getAccount_number() == new_account_number)
            TestUtils.printTestPassed("TC1-setAccount_number");
        else
            TestUtils.printTestFailed("TC1-setAccount_number");

        if (testTransaction.getTransactionAmount() == new_amount)
            TestUtils.printTestPassed("TC2-setTransaction_amount");
        else
            TestUtils.printTestFailed("TC2-setTransaction_amount");

        if (testTransaction.getTransaction_date().equals(newDate))
            TestUtils.printTestPassed("TC3-setTransaction_date");
        else
            TestUtils.printTestFailed("TC3-setTransaction_date");

        // 4-Teardown: none
    }

    public static void testTransactionToString() {
        // 1-Setup
        Date testDate;
        try {
            testDate = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        Transaction testTransaction = new Transaction("5495-1234", 50.21, testDate);

        // 2-Exercise
        String result = testTransaction.toString();

        // 3-Verify
        System.out.println("\nStarting the assertions of the test method: testTransactionToString");

        if (result.contains("5495-1234") && result.contains("50.21"))
            TestUtils.printTestPassed("TC1-toString contains account and amount");
        else
            TestUtils.printTestFailed("TC1-toString contains account and amount");

        // 4-Teardown: none
    }

    public static void main(String[] args) {
        testTransactionConstructor();
        testTransactionSetters();
        testTransactionToString();
    }
}