package tests;

import model.User;
import utils.TestUtils;

public class UserTest {

    public static void testUserConstructor() {
        // 1-Setup
        String test_username = "mike";
        String test_password = "my_passwd";
        String test_first_name = "Mike";
        String test_last_name = "Smith";
        String test_mobile_number = "07771234567";

        // 2-Exercise
        User testUser = new User(test_username, test_password, test_first_name,
                test_last_name, test_mobile_number);

        // 3-Verify
        System.out.println("Starting the assertions of the test method: testUserConstructor");

        // if-else verification
        if (testUser.getUsername() == test_username)
            TestUtils.printTestPassed("TC1-getUsername");
        else
            TestUtils.printTestFailed("TC1-getUsername");


        if (testUser.getPassword() == test_password)
            TestUtils.printTestPassed("TC2-getPassword");
        else
            TestUtils.printTestFailed("TC2-getPassword");

        if (testUser.getFirst_name() == test_first_name)
            TestUtils.printTestPassed("TC3-getFirst_name");
        else
            TestUtils.printTestFailed("TC3-getFirst_name");

        if (testUser.getLast_name() == test_last_name)
            TestUtils.printTestPassed("TC4-getLast_name");
        else
            TestUtils.printTestFailed("TC4-getLast_name");

        if (testUser.getMobile_number() == test_mobile_number)
            TestUtils.printTestPassed("TC5-getMobile_number");
        else
            TestUtils.printTestFailed("TC5-getMobile_number");

        // TODO5: Java assert verification (run with -ea to enable)
        assert testUser.getUsername() == test_username;
        assert testUser.getPassword() == test_password;
        assert testUser.getFirst_name() == test_first_name;
        assert testUser.getLast_name() == test_last_name;
        assert testUser.getMobile_number() == test_mobile_number;

        System.out.println("All Java assertions in the test suite passed (none failed).");
        // 4-Teardown: none
    }

    public static void main(String[] args) {
        testUserConstructor();
    }
}