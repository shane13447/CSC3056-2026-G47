package tests;

import model.Account;
import utils.TestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Unit test class for the Account model (TODO6).
 * One test method per method under test, using if-else verification.
 */
public class AccountTest {

    public static void testAccountConstructor() {
        // 1-Setup
        String test_account_number = "5495-1234";
        String test_username = "mike";
        String test_account_type = "Standard";
        Date test_opening_date;
        try {
            test_opening_date = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        // 2-Exercise
        Account testAccount = new Account(test_account_number, test_username,
                test_account_type, test_opening_date);

        // 3-Verify
        System.out.println("Starting the assertions of the test method: testAccountConstructor");

        if (testAccount.getAccount_number() == test_account_number)
            TestUtils.printTestPassed("TC1-getAccount_number");
        else
            TestUtils.printTestFailed("TC1-getAccount_number");

        if (testAccount.getUsername_of_account_holder() == test_username)
            TestUtils.printTestPassed("TC2-getUsername_of_account_holder");
        else
            TestUtils.printTestFailed("TC2-getUsername_of_account_holder");

        if (testAccount.getAccount_type() == test_account_type)
            TestUtils.printTestPassed("TC3-getAccount_type");
        else
            TestUtils.printTestFailed("TC3-getAccount_type");

        // Date must be compared with .equals(), not == (reference vs value comparison)
        if (testAccount.getAccount_opening_date().equals(test_opening_date))
            TestUtils.printTestPassed("TC4-getAccount_opening_date");
        else
            TestUtils.printTestFailed("TC4-getAccount_opening_date");

        // 4-Teardown: none
    }

    public static void testAccountSetters() {
        // 1-Setup
        Date originalDate;
        Date newDate;
        try {
            originalDate = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
            newDate = new SimpleDateFormat("dd/MM/yyyy").parse("15/06/2021");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        Account testAccount = new Account("0000-0000", "placeholder", "Standard", originalDate);

        String new_account_number = "9999-8888";
        String new_username = "updated@email.com";
        String new_type = "Saving";

        // 2-Exercise
        testAccount.setAccount_number(new_account_number);
        testAccount.setUsername_of_account_holder(new_username);
        testAccount.setAccount_type(new_type);
        testAccount.setAccount_opening_date(newDate);

        // 3-Verify
        System.out.println("\nStarting the assertions of the test method: testAccountSetters");

        if (testAccount.getAccount_number() == new_account_number)
            TestUtils.printTestPassed("TC1-setAccount_number");
        else
            TestUtils.printTestFailed("TC1-setAccount_number");

        if (testAccount.getUsername_of_account_holder() == new_username)
            TestUtils.printTestPassed("TC2-setUsername_of_account_holder");
        else
            TestUtils.printTestFailed("TC2-setUsername_of_account_holder");

        if (testAccount.getAccount_type() == new_type)
            TestUtils.printTestPassed("TC3-setAccount_type");
        else
            TestUtils.printTestFailed("TC3-setAccount_type");

        if (testAccount.getAccount_opening_date().equals(newDate))
            TestUtils.printTestPassed("TC4-setAccount_opening_date");
        else
            TestUtils.printTestFailed("TC4-setAccount_opening_date");

        // 4-Teardown: none
    }

    public static void testAccountToString() {
        // 1-Setup
        Date testDate;
        try {
            testDate = new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }
        Account testAccount = new Account("5495-1234", "mike", "Standard", testDate);

        // 2-Exercise
        String result = testAccount.toString();

        // 3-Verify
        System.out.println("\nStarting the assertions of the test method: testAccountToString");

        if (result.contains("5495-1234") && result.contains("mike") && result.contains("Standard"))
            TestUtils.printTestPassed("TC1-toString contains expected fields");
        else
            TestUtils.printTestFailed("TC1-toString contains expected fields");

        // 4-Teardown: none
    }

    public static void main(String[] args) {
        testAccountConstructor();
        testAccountSetters();
        testAccountToString();
    }
}