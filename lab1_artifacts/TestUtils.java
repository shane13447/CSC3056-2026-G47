package utils;

/**
 * Utility class for test automation helper methods.
 * Provides colored pass/fail output for consistent test reporting.
 */
public class TestUtils {
    public static final String TEXT_COLOR_GREEN = "\u001B[32m";
    public static final String TEXT_COLOR_RED = "\u001B[31m";
    public static final String TEXT_COLOR_RESET = "\u001B[0m";

    // TODO3: refactored println pass/fail into reusable methods
    public static void printTestPassed(String test_case_name) {
        System.out.println(TEXT_COLOR_GREEN + "PASSED -- " + test_case_name + TEXT_COLOR_RESET);
    }

    public static void printTestFailed(String test_case_name) {
        System.out.println(TEXT_COLOR_RED + "FAILED -- " + test_case_name + TEXT_COLOR_RESET);
    }
}