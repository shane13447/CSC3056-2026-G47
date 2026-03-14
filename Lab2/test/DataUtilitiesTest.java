import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

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
    public void calculateColumnTotalShouldReturnSumForValidColumn() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1.0, "R1", "C1");
        data.addValue(2.0, "R2", "C1");
        data.addValue(3.0, "R1", "C2");

        double total = DataUtilities.calculateColumnTotal(data, 0);

        assertEquals(3.0, total, 0.0000001);
    }

    @Test
    public void calculateColumnTotalShouldIgnoreNullCells() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1.0, "R1", "C1");
        data.addValue(null, "R2", "C1");

        double total = DataUtilities.calculateColumnTotal(data, 0);

        assertEquals(1.0, total, 0.0000001);
    }

    @Test
    public void calculateColumnTotalShouldReturnZeroForOutOfRangeColumn() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1.0, "R1", "C1");

        double total = DataUtilities.calculateColumnTotal(data, 10);

        assertEquals(0.0, total, 0.0000001);
    }

    @Test
    public void calculateColumnTotalShouldReturnZeroForNegativeColumn() {
        double total = DataUtilities.calculateColumnTotal(values2D, -1);

        assertEquals(0.0, total, 0.0000001);
    }

    @Test
    public void calculateColumnTotalShouldHandleNegativeValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(-3.5, "R0", "C0");
        data.addValue(1.5, "R1", "C0");

        double total = DataUtilities.calculateColumnTotal(data, 0);

        assertEquals(-2.0, total, 0.0000001);
    }

    @Test
    public void calculateColumnTotalShouldHandleSingleRow() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(9.5, "R0", "C0");

        double total = DataUtilities.calculateColumnTotal(data, 0);

        assertEquals(9.5, total, 0.0000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateColumnTotalShouldThrowWhenDataIsNull() {
        DataUtilities.calculateColumnTotal(null, 0);
    }

    // Tests for calculateRowTotal

    @Test
    public void calculateRowTotalShouldReturnSumForValidRow() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1.5, "R1", "C1");
        data.addValue(2.5, "R1", "C2");
        data.addValue(7.0, "R2", "C1");

        double total = DataUtilities.calculateRowTotal(data, 0);

        assertEquals(4.0, total, 0.0000001);
    }

    @Test
    public void calculateRowTotalShouldIgnoreNullCells() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(2.5, "R1", "C1");
        data.addValue(null, "R1", "C2");

        double total = DataUtilities.calculateRowTotal(data, 0);

        assertEquals(2.5, total, 0.0000001);
    }

    @Test
    public void calculateRowTotalShouldReturnZeroForOutOfRangeRow() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1.0, "R1", "C1");

        double total = DataUtilities.calculateRowTotal(data, 10);

        assertEquals(0.0, total, 0.0000001);
    }

    @Test
    public void calculateRowTotalShouldReturnZeroForNegativeRow() {
        double total = DataUtilities.calculateRowTotal(values2D, -1);

        assertEquals(0.0, total, 0.0000001);
    }

    @Test
    public void calculateRowTotalShouldHandleNegativeValues() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(-2.0, "R0", "C0");
        data.addValue(-3.0, "R0", "C1");

        double total = DataUtilities.calculateRowTotal(data, 0);

        assertEquals(-5.0, total, 0.0000001);
    }

    @Test
    public void calculateRowTotalShouldHandleSingleColumn() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(6.0, "R0", "C0");

        double total = DataUtilities.calculateRowTotal(data, 0);

        assertEquals(6.0, total, 0.0000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateRowTotalShouldThrowWhenDataIsNull() {
        DataUtilities.calculateRowTotal(null, 0);
    }

    // Tests for createNumberArray

    @Test
    public void createNumberArrayShouldConvertPrimitiveArrayToNumberArray() {
        double[] input = new double[] {1.0, -2.5, 3.25};

        Number[] numbers = DataUtilities.createNumberArray(input);

        assertArrayEquals(new Number[] {1.0, -2.5, 3.25}, numbers);
    }

    @Test
    public void createNumberArrayShouldPreserveSingleElementInput() {
        double[] input = new double[] {42.0};

        Number[] numbers = DataUtilities.createNumberArray(input);

        assertArrayEquals(new Number[] {42.0}, numbers);
    }

    @Test
    public void createNumberArrayShouldHandleEmptyArray() {
        Number[] numbers = DataUtilities.createNumberArray(new double[] {});

        assertEquals(0, numbers.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNumberArrayShouldThrowWhenInputIsNull() {
        DataUtilities.createNumberArray(null);
    }

    // Tests for createNumberArray2D

    @Test
    public void createNumberArray2DShouldConvertPrimitive2DArrayToNumber2DArray() {
        double[][] input = new double[][] {
            {1.0, 2.0},
            {-3.5, 4.5}
        };

        Number[][] numbers = DataUtilities.createNumberArray2D(input);

        assertEquals(2, numbers.length);
        assertArrayEquals(new Number[] {1.0, 2.0}, numbers[0]);
        assertArrayEquals(new Number[] {-3.5, 4.5}, numbers[1]);
    }

    @Test
    public void createNumberArray2DShouldPreserveRowLengths() {
        double[][] input = new double[][] {
            {1.0},
            {2.0, 3.0}
        };

        Number[][] numbers = DataUtilities.createNumberArray2D(input);

        assertEquals(1, numbers[0].length);
        assertEquals(2, numbers[1].length);
    }

    @Test
    public void createNumberArray2DShouldHandleEmptyArray() {
        Number[][] numbers = DataUtilities.createNumberArray2D(new double[][] {});

        assertEquals(0, numbers.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNumberArray2DShouldThrowWhenInputIsNull() {
        DataUtilities.createNumberArray2D(null);
    }

    // Tests for getCumulativePercentages

    @Test
    public void getCumulativePercentagesShouldMatchJavadocExample() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 5.0);
        data.addValue("B", 9.0);
        data.addValue("C", 2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.3125, result.getValue("A").doubleValue(), 0.0000001);
        assertEquals(0.875, result.getValue("B").doubleValue(), 0.0000001);
        assertEquals(1.0, result.getValue("C").doubleValue(), 0.0000001);
    }

    @Test
    public void getCumulativePercentagesShouldEndAtOneForPositiveValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("X", 1.0);
        data.addValue("Y", 2.0);
        data.addValue("Z", 3.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue("Z").doubleValue(), 0.0000001);
    }

    @Test
    public void getCumulativePercentagesShouldHandleSingleValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 10.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(1.0, result.getValue("A").doubleValue(), 0.0000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getCumulativePercentagesShouldThrowWhenInputIsNull() {
        DataUtilities.getCumulativePercentages(null);
    }
}
