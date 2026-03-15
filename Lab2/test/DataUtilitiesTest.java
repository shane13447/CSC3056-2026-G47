import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class DataUtilitiesTest {

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

    private Values2D values2D;

    @Before
    public void setUp() {
        DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
        testValues.addValue(1, 0, 0);
        testValues.addValue(4, 1, 0);
        values2D = testValues;
    }

    @After
    public void tearDown() {
        values2D = null;
    }

    // Tests for calculateColumnTotal

    @Test
    public void calculateColumnTotalShouldReturnSumForValidColumn() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(1.0, "R1", "C1");
            data.addValue(2.0, "R2", "C1");
            data.addValue(3.0, "R1", "C2");
                        double total = DataUtilities.calculateColumnTotal(data, 0);
                        assertEquals(3.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateColumnTotalShouldIgnoreNullCells() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(1.0, "R1", "C1");
            data.addValue(null, "R2", "C1");
                        double total = DataUtilities.calculateColumnTotal(data, 0);
                        assertEquals(1.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateColumnTotalShouldReturnZeroForOutOfRangeColumn() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(1.0, "R1", "C1");
                        double total = DataUtilities.calculateColumnTotal(data, 10);
                        assertEquals(0.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateColumnTotalShouldReturnZeroForNegativeColumn() {
        try {
            double total = DataUtilities.calculateColumnTotal(values2D, -1);
                        assertEquals(0.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateColumnTotalShouldHandleNegativeValues() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(-3.5, "R0", "C0");
            data.addValue(1.5, "R1", "C0");
                        double total = DataUtilities.calculateColumnTotal(data, 0);
                        assertEquals(-2.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateColumnTotalShouldHandleSingleRow() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(9.5, "R0", "C0");
                        double total = DataUtilities.calculateColumnTotal(data, 0);
                        assertEquals(9.5, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateColumnTotalShouldThrowWhenDataIsNull() {
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("Expected IllegalArgumentException but none was thrown.");
        } catch (IllegalArgumentException e) {
            // expected path
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // Tests for calculateRowTotal

    @Test
    public void calculateRowTotalShouldReturnSumForValidRow() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(1.5, "R1", "C1");
            data.addValue(2.5, "R1", "C2");
            data.addValue(7.0, "R2", "C1");
                        double total = DataUtilities.calculateRowTotal(data, 0);
                        assertEquals(4.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateRowTotalShouldIgnoreNullCells() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(2.5, "R1", "C1");
            data.addValue(null, "R1", "C2");
                        double total = DataUtilities.calculateRowTotal(data, 0);
                        assertEquals(2.5, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateRowTotalShouldReturnZeroForOutOfRangeRow() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(1.0, "R1", "C1");
                        double total = DataUtilities.calculateRowTotal(data, 10);
                        assertEquals(0.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateRowTotalShouldReturnZeroForNegativeRow() {
        try {
            double total = DataUtilities.calculateRowTotal(values2D, -1);
                        assertEquals(0.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateRowTotalShouldHandleNegativeValues() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(-2.0, "R0", "C0");
            data.addValue(-3.0, "R0", "C1");
                        double total = DataUtilities.calculateRowTotal(data, 0);
                        assertEquals(-5.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateRowTotalShouldHandleSingleColumn() {
        try {
            DefaultKeyedValues2D data = new DefaultKeyedValues2D();
            data.addValue(6.0, "R0", "C0");
                        double total = DataUtilities.calculateRowTotal(data, 0);
                        assertEquals(6.0, total, 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void calculateRowTotalShouldThrowWhenDataIsNull() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("Expected IllegalArgumentException but none was thrown.");
        } catch (IllegalArgumentException e) {
            // expected path
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // Tests for createNumberArray

    @Test
    public void createNumberArrayShouldConvertPrimitiveArrayToNumberArray() {
        try {
            double[] input = new double[] {1.0, -2.5, 3.25};
                        Number[] numbers = DataUtilities.createNumberArray(input);
                        assertArrayEquals(new Number[] {1.0, -2.5, 3.25}, numbers);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void createNumberArrayShouldPreserveSingleElementInput() {
        try {
            double[] input = new double[] {42.0};
                        Number[] numbers = DataUtilities.createNumberArray(input);
                        assertArrayEquals(new Number[] {42.0}, numbers);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void createNumberArrayShouldHandleEmptyArray() {
        try {
            Number[] numbers = DataUtilities.createNumberArray(new double[] {});
                        assertEquals(0, numbers.length);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void createNumberArrayShouldThrowWhenInputIsNull() {
        try {
            DataUtilities.createNumberArray(null);
            fail("Expected IllegalArgumentException but none was thrown.");
        } catch (IllegalArgumentException e) {
            // expected path
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // Tests for createNumberArray2D

    @Test
    public void createNumberArray2DShouldConvertPrimitive2DArrayToNumber2DArray() {
        try {
            double[][] input = new double[][] {
            {1.0, 2.0},
            {-3.5, 4.5}
            };
                        Number[][] numbers = DataUtilities.createNumberArray2D(input);
                        assertEquals(2, numbers.length);
            assertArrayEquals(new Number[] {1.0, 2.0}, numbers[0]);
            assertArrayEquals(new Number[] {-3.5, 4.5}, numbers[1]);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void createNumberArray2DShouldPreserveRowLengths() {
        try {
            double[][] input = new double[][] {
            {1.0},
            {2.0, 3.0}
            };
                        Number[][] numbers = DataUtilities.createNumberArray2D(input);
                        assertEquals(1, numbers[0].length);
            assertEquals(2, numbers[1].length);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void createNumberArray2DShouldHandleEmptyArray() {
        try {
            Number[][] numbers = DataUtilities.createNumberArray2D(new double[][] {});
                        assertEquals(0, numbers.length);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void createNumberArray2DShouldThrowWhenInputIsNull() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("Expected IllegalArgumentException but none was thrown.");
        } catch (IllegalArgumentException e) {
            // expected path
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    // Tests for getCumulativePercentages

    @Test
    public void getCumulativePercentagesShouldMatchJavadocExample() {
        try {
            DefaultKeyedValues data = new DefaultKeyedValues();
            data.addValue("A", 5.0);
            data.addValue("B", 9.0);
            data.addValue("C", 2.0);
                        KeyedValues result = DataUtilities.getCumulativePercentages(data);
                        assertEquals(0.3125, result.getValue("A").doubleValue(), 0.0000001);
            assertEquals(0.875, result.getValue("B").doubleValue(), 0.0000001);
            assertEquals(1.0, result.getValue("C").doubleValue(), 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void getCumulativePercentagesShouldEndAtOneForPositiveValues() {
        try {
            DefaultKeyedValues data = new DefaultKeyedValues();
            data.addValue("X", 1.0);
            data.addValue("Y", 2.0);
            data.addValue("Z", 3.0);
                        KeyedValues result = DataUtilities.getCumulativePercentages(data);
                        assertEquals(1.0, result.getValue("Z").doubleValue(), 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void getCumulativePercentagesShouldHandleSingleValue() {
        try {
            DefaultKeyedValues data = new DefaultKeyedValues();
            data.addValue("A", 10.0);
                        KeyedValues result = DataUtilities.getCumulativePercentages(data);
                        assertEquals(1.0, result.getValue("A").doubleValue(), 0.0000001);
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    @Test
    public void getCumulativePercentagesShouldThrowWhenInputIsNull() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("Expected IllegalArgumentException but none was thrown.");
        } catch (IllegalArgumentException e) {
            // expected path
        } catch (AssertionError ae) {
            fail("Inconsistency found - Result is different from documented behaviour: " + ae.getMessage());
        } catch (Exception e) {
            fail("Unexpected error (counted as failure): " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}
