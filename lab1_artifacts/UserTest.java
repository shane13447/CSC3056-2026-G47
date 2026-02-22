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

        if (testUser.getUsername() == test_username)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getUsername-Passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getUsername-FAILED" + TestUtils.TEXT_COLOR_RESET);

        if (testUser.getPassword() == test_password)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getPassword-Passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getPassword-FAILED" + TestUtils.TEXT_COLOR_RESET);

        if (testUser.getFirst_name() == test_first_name)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getFirst_name-Passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getFirst_name-FAILED" + TestUtils.TEXT_COLOR_RESET);

        if (testUser.getLast_name() == test_last_name)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getLast_name-Passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getLast_name-FAILED" + TestUtils.TEXT_COLOR_RESET);

        if (testUser.getMobile_number() == test_mobile_number)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC5-getMobile_number-Passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC5-getMobile_number-FAILED" + TestUtils.TEXT_COLOR_RESET);

        // 4-Teardown: none
    }

    public static void main(String[] args) {
        testUserConstructor();
    }
}