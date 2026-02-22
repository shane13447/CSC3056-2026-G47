package utils;


import model.User;
import utils.TestUtils;

public class UserTest {

    public static void main(String[] args) {
        // move all the test code of User Constructor from the main() to this method
		/* OLD: manual testing
		User testUser = new User("mike", "my_passwd", "Mike", "Smith", "07771234567");
		System.out.println(testUser);
		*/

        // automated testing
        //1-Setup
        String test_username = "mike";
        String test_password = "my_passwd";
        String test_first_name = "Mike";
        String test_last_name = "Smith";
        String test_mobile_number = "07771234567";

        // 2-Exercise, run the object under test (constructor)
        User testUser = new User(test_username, test_password, test_first_name,
                test_last_name, test_mobile_number);

        // 3- Verify (Assert)
        System.out.println("Starting the assertions of the test method: testUserConstructor");

        if (testUser.getUsername() == test_username)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getUsername-Passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getUsername-FAILED" + TestUtils.TEXT_COLOR_RESET);

        if (testUser.getPassword() == test_password)
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getPassword-Passed" + TestUtils.TEXT_COLOR_RESET);
        else
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getPassword-FAILED" + TestUtils.TEXT_COLOR_RESET);
    }
}