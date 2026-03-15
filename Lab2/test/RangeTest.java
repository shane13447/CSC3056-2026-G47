import static org.junit.Assert.*;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class RangeTest {

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

    private Range rangeObjectUnderTest;

    @Before
    public void setUp() throws Exception {
        rangeObjectUnderTest = new Range(-1, 1);
    }

    @After
    public void tearDown() throws Exception {
        rangeObjectUnderTest = null;
    }

    // ==========================================
    // Tests for constrain
    // ==========================================

    @Test
    public void testConstrainValueWithinRange() {
        try {
            assertEquals("constrain: value within range should return the value itself",
            0.0, rangeObjectUnderTest.constrain(0.0), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testConstrainValueBelowLowerBound() {
        try {
            assertEquals("constrain: value below range should return lower bound",
            -1.0, rangeObjectUnderTest.constrain(-10.0), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testConstrainValueAboveUpperBound() {
        try {
            assertEquals("constrain: value above range should return upper bound",
            1.0, rangeObjectUnderTest.constrain(10.0), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testConstrainValueAtLowerBound() {
        try {
            assertEquals("constrain: value at lower bound should return lower bound",
            -1.0, rangeObjectUnderTest.constrain(-1.0), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testConstrainValueAtUpperBound() {
        try {
            assertEquals("constrain: value at upper bound should return upper bound",
            1.0, rangeObjectUnderTest.constrain(1.0), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testConstrainValueJustBelowLowerBound() {
        try {
            assertEquals("constrain: value just below lower bound should return lower bound",
            -1.0, rangeObjectUnderTest.constrain(-1.0001), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testConstrainValueJustAboveUpperBound() {
        try {
            assertEquals("constrain: value just above upper bound should return upper bound",
            1.0, rangeObjectUnderTest.constrain(1.0001), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // ==========================================
    // Tests for intersects
    // ==========================================

    @Test
    public void testIntersectsOverlappingFromBelow() {
        try {
            assertTrue("intersects: overlapping range from below should return true",
            rangeObjectUnderTest.intersects(-2.0, 0.0));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testIntersectsOverlappingFromAbove() {
        try {
            assertTrue("intersects: overlapping range from above should return true",
            rangeObjectUnderTest.intersects(0.0, 2.0));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testIntersectsCompletelyBelow() {
        try {
            assertFalse("intersects: range completely below should return false",
            rangeObjectUnderTest.intersects(-5.0, -2.0));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testIntersectsCompletelyAbove() {
        try {
            assertFalse("intersects: range completely above should return false",
            rangeObjectUnderTest.intersects(2.0, 5.0));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testIntersectsEnclosingRange() {
        try {
            assertTrue("intersects: range that encloses should return true",
            rangeObjectUnderTest.intersects(-5.0, 5.0));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testIntersectsInsideRange() {
        try {
            assertTrue("intersects: range fully inside should return true",
            rangeObjectUnderTest.intersects(-0.5, 0.5));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testIntersectsSameRange() {
        try {
            assertTrue("intersects: same range should return true",
            rangeObjectUnderTest.intersects(-1.0, 1.0));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testIntersectsTouchingAtLowerBound() {
        try {
            assertTrue("intersects: range touching at lower bound should return true",
            rangeObjectUnderTest.intersects(-3.0, -1.0));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testIntersectsTouchingAtUpperBound() {
        try {
            assertTrue("intersects: range touching at upper bound should return true",
            rangeObjectUnderTest.intersects(1.0, 3.0));
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // ==========================================
    // Tests for expandToInclude
    // ==========================================

    @Test
    public void testExpandToIncludeValueBelowRange() {
        try {
            Range result = Range.expandToInclude(rangeObjectUnderTest, -5.0);
                        assertEquals("expandToInclude: lower bound should expand to -5.0",
            -5.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expandToInclude: upper bound should remain 1.0",
            1.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandToIncludeValueAboveRange() {
        try {
            Range result = Range.expandToInclude(rangeObjectUnderTest, 5.0);
                        assertEquals("expandToInclude: lower bound should remain -1.0",
            -1.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expandToInclude: upper bound should expand to 5.0",
            5.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandToIncludeValueInsideRange() {
        try {
            Range result = Range.expandToInclude(rangeObjectUnderTest, 0.0);
                        assertEquals("expandToInclude: lower bound should remain -1.0",
            -1.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expandToInclude: upper bound should remain 1.0",
            1.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandToIncludeNullRange() {
        try {
            Range result = Range.expandToInclude(null, 7.5);
                        assertEquals("expandToInclude: null range lower should be 7.5",
            7.5, result.getLowerBound(), 0.0000001d);
            assertEquals("expandToInclude: null range upper should be 7.5",
            7.5, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandToIncludeValueAtLowerBound() {
        try {
            try {
            Range result = Range.expandToInclude(rangeObjectUnderTest, -1.0);
                        assertEquals("expandToInclude: value at lower bound, lower should remain -1.0",
            -1.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expandToInclude: value at lower bound, upper should remain 1.0",
            1.0, result.getUpperBound(), 0.0000001d);
            } catch (Exception e) {
            fail("SUT threw " + e.getClass().getName()
            + " when expanding to include value at lower bound: " + e.getMessage());
            }
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandToIncludeValueAtUpperBound() {
        try {
            try {
            Range result = Range.expandToInclude(rangeObjectUnderTest, 1.0);
                        assertEquals("expandToInclude: value at upper bound, lower should remain -1.0",
            -1.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expandToInclude: value at upper bound, upper should remain 1.0",
            1.0, result.getUpperBound(), 0.0000001d);
            } catch (Exception e) {
            fail("SUT threw " + e.getClass().getName()
            + " when expanding to include value at upper bound: " + e.getMessage());
            }
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // ==========================================
    // Tests for expand
    // ==========================================

    @Test
    public void testExpandJavadocExample() {
        try {
            Range base = new Range(2, 6);
            Range result = Range.expand(base, 0.25, 0.50);
                        assertEquals("expand: Javadoc example lower should be 1.0",
            1.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expand: Javadoc example upper should be 8.0",
            8.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandZeroMargins() {
        try {
            Range result = Range.expand(rangeObjectUnderTest, 0.0, 0.0);
                        assertEquals("expand: zero margins lower should remain -1.0",
            -1.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expand: zero margins upper should remain 1.0",
            1.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandEqualMargins() {
        try {
            Range base = new Range(0, 10);
            Range result = Range.expand(base, 0.10, 0.10);
                        assertEquals("expand: symmetric 10% lower should be -1.0",
            -1.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expand: symmetric 10% upper should be 11.0",
            11.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandLargeMargins() {
        try {
            Range base = new Range(0, 10);
            Range result = Range.expand(base, 1.0, 1.0);
                        assertEquals("expand: 100% margins lower should be -10.0",
            -10.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expand: 100% margins upper should be 20.0",
            20.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandNullRange() {
        try {
            try {
            Range.expand(null, 0.25, 0.50);
            fail("No exception thrown. The expected outcome was: a thrown "
            + "exception of type: IllegalArgumentException");
            } catch (Exception e) {
            assertTrue("An exception should be thrown for null range",
            e instanceof Exception);
            }
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandOnlyLowerMargin() {
        try {
            Range base = new Range(2, 6);
            Range result = Range.expand(base, 0.5, 0.0);
                        assertEquals("expand: 50% lower margin, lower should be 0.0",
            0.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expand: 0% upper margin, upper should be 6.0",
            6.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testExpandOnlyUpperMargin() {
        try {
            Range base = new Range(2, 6);
            Range result = Range.expand(base, 0.0, 0.5);
                        assertEquals("expand: 0% lower margin, lower should be 2.0",
            2.0, result.getLowerBound(), 0.0000001d);
            assertEquals("expand: 50% upper margin, upper should be 8.0",
            8.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // ==========================================
    // Tests for shift (with allowZeroCrossing)
    // ==========================================

    @Test
    public void testShiftPositiveDeltaAllowZeroCrossing() {
        try {
            Range base = new Range(-2.0, 3.0);
            Range result = Range.shift(base, 5.0, true);
                        assertEquals("shift: positive delta allow crossing, lower should be 3.0",
            3.0, result.getLowerBound(), 0.0000001d);
            assertEquals("shift: positive delta allow crossing, upper should be 8.0",
            8.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testShiftNegativeDeltaAllowZeroCrossing() {
        try {
            Range base = new Range(-2.0, 3.0);
            Range result = Range.shift(base, -4.0, true);
                        assertEquals("shift: negative delta allow crossing, lower should be -6.0",
            -6.0, result.getLowerBound(), 0.0000001d);
            assertEquals("shift: negative delta allow crossing, upper should be -1.0",
            -1.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testShiftZeroDelta() {
        try {
            Range result = Range.shift(rangeObjectUnderTest, 0.0, true);
                        assertEquals("shift: zero delta lower should remain -1.0",
            -1.0, result.getLowerBound(), 0.0000001d);
            assertEquals("shift: zero delta upper should remain 1.0",
            1.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testShiftDisallowZeroCrossingUpperBoundClamped() {
        try {
            Range base = new Range(-2.0, 3.0);
            Range result = Range.shift(base, -4.0, false);
                        assertEquals("shift: disallow crossing, lower should be -6.0",
            -6.0, result.getLowerBound(), 0.0000001d);
            assertEquals("shift: disallow crossing, upper should clamp to 0.0",
            0.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testShiftDisallowZeroCrossingLowerBoundClamped() {
        try {
            Range base = new Range(-3.0, -1.0);
            Range result = Range.shift(base, 5.0, false);
                        assertEquals("shift: disallow crossing, lower should clamp to 0.0",
            0.0, result.getLowerBound(), 0.0000001d);
            assertEquals("shift: disallow crossing, upper should clamp to 0.0",
            0.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testShiftPositiveRangePositiveDeltaNoZeroCrossing() {
        try {
            Range base = new Range(2.0, 5.0);
            Range result = Range.shift(base, 3.0, false);
                        assertEquals("shift: positive range positive delta, lower should be 5.0",
            5.0, result.getLowerBound(), 0.0000001d);
            assertEquals("shift: positive range positive delta, upper should be 8.0",
            8.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testShiftNullBase() {
        try {
            try {
            Range.shift(null, 1.0, true);
            fail("No exception thrown. The expected outcome was: a thrown "
            + "exception of type: IllegalArgumentException");
            } catch (Exception e) {
            assertTrue("An exception should be thrown for null base",
            e instanceof Exception);
            }
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void testShiftNegativeRangeNegativeDeltaNoZeroCrossing() {
        try {
            Range base = new Range(-5.0, -2.0);
            Range result = Range.shift(base, -3.0, false);
                        assertEquals("shift: negative range negative delta, lower should be -8.0",
            -8.0, result.getLowerBound(), 0.0000001d);
            assertEquals("shift: negative range negative delta, upper should be -5.0",
            -5.0, result.getUpperBound(), 0.0000001d);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}