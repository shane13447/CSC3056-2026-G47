import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest {

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
    public void testCalculateColumnTotalValidDataAndColumnZero() {
        assertEquals("Wrong sum returned. It should be 5.0",
                5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
    }

    @Test
    public void testCalculateColumnTotalValidDataMultipleColumns() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(7.5, "R0", "C0");
        data.addValue(2.5, "R1", "C0");
        data.addValue(5.0, "R0", "C1");
        data.addValue(3.0, "R1", "C1");

        assertEquals("Column 1 sum should be 8.0",
                8.0, DataUtilities.calculateColumnTotal(data, 1), 0.0000001d);
    }

    @Test
    public void testCalculateColumnTotalWithNegativeValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(-3.5, "R0", "C0");
        data.addValue(1.5, "R1", "C0");

        assertEquals("Sum of column with negatives should be -2.0",
                -2.0, DataUtilities.calculateColumnTotal(data, 0), 0.0000001d);
    }

    @Test
    public void testCalculateColumnTotalOutOfRangeColumnIndex() {
        try {
            double result = DataUtilities.calculateColumnTotal(values2D, 10);
            assertEquals("Out-of-range column should return 0.0",
                    0.0, result, 0.0000001d);
        } catch (Exception e) {
            fail("SUT threw " + e.getClass().getName()
                    + " for out-of-range column index instead of returning 0.0");
        }
    }

    @Test
    public void testCalculateColumnTotalNegativeColumnIndex() {
        try {
            double result = DataUtilities.calculateColumnTotal(values2D, -1);
            assertEquals("Negative column index should return 0.0",
                    0.0, result, 0.0000001d);
        } catch (Exception e) {
            fail("SUT threw " + e.getClass().getName()
                    + " for negative column index instead of returning 0.0");
        }
    }

    @Test
    public void testCalculateColumnTotalNullData() {
        try {
            DataUtilities.calculateColumnTotal(null, 0);
            fail("No exception thrown. The expected outcome was: a thrown "
                    + "exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCalculateColumnTotalSingleRowTable() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(9.5, "R0", "C0");

        assertEquals("Single row column total should be 9.5",
                9.5, DataUtilities.calculateColumnTotal(data, 0), 0.0000001d);
    }


    // Tests for calculateRowTotal


    @Test
    public void testCalculateRowTotalValidDataAndRowZero() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1.5, "R0", "C0");
        data.addValue(2.5, "R0", "C1");
        data.addValue(7.0, "R1", "C0");

        assertEquals("Row 0 total should be 4.0",
                4.0, DataUtilities.calculateRowTotal(data, 0), 0.0000001d);
    }

    @Test
    public void testCalculateRowTotalValidDataMultipleColumns() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(3.0, "R0", "C0");
        data.addValue(4.0, "R0", "C1");
        data.addValue(5.0, "R0", "C2");

        assertEquals("Row 0 total should be 12.0",
                12.0, DataUtilities.calculateRowTotal(data, 0), 0.0000001d);
    }

    @Test
    public void testCalculateRowTotalWithNegativeValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(-2.0, "R0", "C0");
        data.addValue(-3.0, "R0", "C1");

        assertEquals("Row total with negatives should be -5.0",
                -5.0, DataUtilities.calculateRowTotal(data, 0), 0.0000001d);
    }

    @Test
    public void testCalculateRowTotalOutOfRangeRowIndex() {
        try {
            double result = DataUtilities.calculateRowTotal(values2D, 10);
            assertEquals("Out-of-range row should return 0.0",
                    0.0, result, 0.0000001d);
        } catch (Exception e) {
            fail("SUT threw " + e.getClass().getName()
                    + " for out-of-range row index instead of returning 0.0");
        }
    }

    @Test
    public void testCalculateRowTotalNegativeRowIndex() {
        try {
            double result = DataUtilities.calculateRowTotal(values2D, -1);
            assertEquals("Negative row index should return 0.0",
                    0.0, result, 0.0000001d);
        } catch (Exception e) {
            fail("SUT threw " + e.getClass().getName()
                    + " for negative row index instead of returning 0.0");
        }
    }

    @Test
    public void testCalculateRowTotalNullData() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
            fail("No exception thrown. The expected outcome was: a thrown "
                    + "exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCalculateRowTotalSingleColumnTable() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(6.0, "R0", "C0");

        assertEquals("Single column row total should be 6.0",
                6.0, DataUtilities.calculateRowTotal(data, 0), 0.0000001d);
    }


    // Tests for createNumberArray


    @Test
    public void testCreateNumberArrayValidPositiveData() {
        double[] data = {1.0, 2.5, 3.75};
        Number[] result = DataUtilities.createNumberArray(data);

        assertEquals("Array length should be 3", 3, result.length);
        assertNotNull("Element 0 should not be null", result[0]);
        assertEquals("Element 0 should be 1.0", 1.0, result[0].doubleValue(), 0.0000001d);
        assertNotNull("Element 1 should not be null", result[1]);
        assertEquals("Element 1 should be 2.5", 2.5, result[1].doubleValue(), 0.0000001d);
        assertNotNull("Element 2 should not be null", result[2]);
        assertEquals("Element 2 should be 3.75", 3.75, result[2].doubleValue(), 0.0000001d);
    }

    @Test
    public void testCreateNumberArrayWithNegativeValues() {
        double[] data = {-1.0, -2.5, 0.0};
        Number[] result = DataUtilities.createNumberArray(data);

        assertNotNull("Element 0 should not be null", result[0]);
        assertEquals("Element 0 should be -1.0", -1.0, result[0].doubleValue(), 0.0000001d);
        assertNotNull("Element 1 should not be null", result[1]);
        assertEquals("Element 1 should be -2.5", -2.5, result[1].doubleValue(), 0.0000001d);
        assertNotNull("Element 2 should not be null", result[2]);
        assertEquals("Element 2 should be 0.0", 0.0, result[2].doubleValue(), 0.0000001d);
    }

    @Test
    public void testCreateNumberArrayEmptyArray() {
        double[] data = {};
        Number[] result = DataUtilities.createNumberArray(data);

        assertEquals("Empty input should return empty array", 0, result.length);
    }

    @Test
    public void testCreateNumberArraySingleElement() {
        double[] data = {42.0};
        Number[] result = DataUtilities.createNumberArray(data);

        assertEquals("Array length should be 1", 1, result.length);
        assertNotNull("Element 0 should not be null", result[0]);
        assertEquals("Element 0 should be 42.0", 42.0, result[0].doubleValue(), 0.0000001d);
    }

    @Test
    public void testCreateNumberArrayNullInput() {
        try {
            DataUtilities.createNumberArray(null);
            fail("No exception thrown. The expected outcome was: a thrown "
                    + "exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }


    // Tests for createNumberArray2D


    @Test
    public void testCreateNumberArray2DValidData() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertEquals("Row count should be 2", 2, result.length);
        assertEquals("Row 0 col count should be 2", 2, result[0].length);
        assertNotNull("Element [0][0] should not be null", result[0][0]);
        assertEquals("Element [0][0] should be 1.0", 1.0, result[0][0].doubleValue(), 0.0000001d);
        assertNotNull("Element [1][1] should not be null", result[1][1]);
        assertEquals("Element [1][1] should be 4.0", 4.0, result[1][1].doubleValue(), 0.0000001d);
    }

    @Test
    public void testCreateNumberArray2DWithNegativeValues() {
        double[][] data = {{-1.5, 2.5}, {-3.5, 4.5}};
        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertNotNull("Element [0][0] should not be null", result[0][0]);
        assertEquals("Element [0][0] should be -1.5", -1.5, result[0][0].doubleValue(), 0.0000001d);
        assertNotNull("Element [0][1] should not be null", result[0][1]);
        assertEquals("Element [0][1] should be 2.5", 2.5, result[0][1].doubleValue(), 0.0000001d);
    }

    @Test
    public void testCreateNumberArray2DSingleRow() {
        double[][] data = {{5.0, 10.0, 15.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertEquals("Row count should be 1", 1, result.length);
        assertEquals("Column count should be 3", 3, result[0].length);
        assertNotNull("Element [0][2] should not be null", result[0][2]);
        assertEquals("Element [0][2] should be 15.0", 15.0, result[0][2].doubleValue(), 0.0000001d);
    }

    @Test
    public void testCreateNumberArray2DEmptyArray() {
        double[][] data = {};
        Number[][] result = DataUtilities.createNumberArray2D(data);

        assertEquals("Empty input should return empty 2D array", 0, result.length);
    }

    @Test
    public void testCreateNumberArray2DNullInput() {
        try {
            DataUtilities.createNumberArray2D(null);
            fail("No exception thrown. The expected outcome was: a thrown "
                    + "exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }


    // Tests for getCumulativePercentages


    @Test
    public void testGetCumulativePercentagesJavadocExample() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, 5.0);
        data.addValue((Comparable) 1, 9.0);
        data.addValue((Comparable) 2, 2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals("Key 0 cumulative % should be 0.3125",
                0.3125, result.getValue(0).doubleValue(), 0.0000001d);
        assertEquals("Key 1 cumulative % should be 0.875",
                0.875, result.getValue(1).doubleValue(), 0.0000001d);
        assertEquals("Key 2 cumulative % should be 1.0",
                1.0, result.getValue(2).doubleValue(), 0.0000001d);
    }

    @Test
    public void testGetCumulativePercentagesSingleValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, 10.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals("Single value cumulative % should be 1.0",
                1.0, result.getValue(0).doubleValue(), 0.0000001d);
    }

    @Test
    public void testGetCumulativePercentagesTwoEqualValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, 5.0);
        data.addValue((Comparable) 1, 5.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals("Key 0 cumulative % should be 0.5",
                0.5, result.getValue(0).doubleValue(), 0.0000001d);
        assertEquals("Key 1 cumulative % should be 1.0",
                1.0, result.getValue(1).doubleValue(), 0.0000001d);
    }

    @Test
    public void testGetCumulativePercentagesNullInput() {
        try {
            DataUtilities.getCumulativePercentages(null);
            fail("No exception thrown. The expected outcome was: a thrown "
                    + "exception of type: IllegalArgumentException");
        } catch (Exception e) {
            assertTrue("Incorrect exception type thrown",
                    e.getClass().equals(IllegalArgumentException.class));
        }
    }

    @Test
    public void testGetCumulativePercentagesLastKeyIsAlwaysOne() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue((Comparable) 0, 1.0);
        data.addValue((Comparable) 1, 2.0);
        data.addValue((Comparable) 2, 3.0);
        data.addValue((Comparable) 3, 4.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals("Last key cumulative % should always be 1.0",
                1.0, result.getValue(3).doubleValue(), 0.0000001d);
    }
}