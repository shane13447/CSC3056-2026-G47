import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class SampleSetupTest {

    private static final String GREEN = "\u001B[32m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    @Rule
    public TestWatcher colorOutput = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println(GREEN + "PASS " + description.getMethodName() + RESET);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            String msg = e == null ? "" : String.valueOf(e.getMessage());
            if (msg.startsWith("Inconsistency found - Result is different from documented behaviour:")) {
                System.out.println(YELLOW + "ASSERTION_MISMATCH " + description.getMethodName() + RESET);
            } else if (msg.startsWith("Unexpected error (counted as failure):")) {
                System.out.println(RED + "EXCEPTION_CAUGHT " + description.getMethodName() + RESET);
            } else {
                System.out.println(RED + "FAIL " + description.getMethodName() + RESET);
            }
        }
    };

    @Test
    public void testEnvironmentSetupShouldWork() {
        try {
            assertTrue("Sample test should pass", true);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
