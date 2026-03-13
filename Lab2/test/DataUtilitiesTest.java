import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.junit.Test;

public class DataUtilitiesTest {

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
    public void calculateColumnTotalShouldReturnZeroForOutOfRangeColumn() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1.0, "R1", "C1");

        double total = DataUtilities.calculateColumnTotal(data, 10);

        assertEquals(0.0, total, 0.0000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateColumnTotalShouldThrowWhenDataIsNull() {
        DataUtilities.calculateColumnTotal(null, 0);
    }

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
    public void calculateRowTotalShouldReturnZeroForOutOfRangeRow() {
        DefaultKeyedValues2D data = new DefaultKeyedValues2D();
        data.addValue(1.0, "R1", "C1");

        double total = DataUtilities.calculateRowTotal(data, 10);

        assertEquals(0.0, total, 0.0000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateRowTotalShouldThrowWhenDataIsNull() {
        DataUtilities.calculateRowTotal(null, 0);
    }

    @Test
    public void createNumberArrayShouldConvertPrimitiveArrayToNumberArray() {
        double[] input = new double[] {1.0, -2.5, 3.25};

        Number[] numbers = DataUtilities.createNumberArray(input);

        assertArrayEquals(new Number[] {1.0, -2.5, 3.25}, numbers);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createNumberArrayShouldThrowWhenInputIsNull() {
        DataUtilities.createNumberArray(null);
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void createNumberArray2DShouldThrowWhenInputIsNull() {
        DataUtilities.createNumberArray2D(null);
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void getCumulativePercentagesShouldThrowWhenInputIsNull() {
        DataUtilities.getCumulativePercentages(null);
    }
}
