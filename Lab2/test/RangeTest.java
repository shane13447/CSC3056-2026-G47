import static org.junit.Assert.assertEquals;

import org.jfree.data.Range;
import org.junit.Test;

public class RangeTest {

    @Test
    public void constrainShouldReturnLowerBoundWhenValueIsBelowRange() {
        Range range = new Range(1.0, 5.0);

        double result = range.constrain(-2.0);

        assertEquals(1.0, result, 0.0000001);
    }

    @Test
    public void constrainShouldReturnUpperBoundWhenValueIsAboveRange() {
        Range range = new Range(1.0, 5.0);

        double result = range.constrain(8.0);

        assertEquals(5.0, result, 0.0000001);
    }

    @Test
    public void intersectsShouldReturnTrueWhenIntervalOverlaps() {
        Range range = new Range(1.0, 5.0);

        boolean result = range.intersects(4.0, 9.0);

        assertEquals(true, result);
    }

    @Test
    public void intersectsShouldReturnFalseWhenIntervalIsDisjoint() {
        Range range = new Range(1.0, 5.0);

        boolean result = range.intersects(6.0, 8.0);

        assertEquals(false, result);
    }

    @Test
    public void expandToIncludeShouldCreateSinglePointRangeWhenBaseIsNull() {
        Range result = Range.expandToInclude(null, 7.5);

        assertEquals(7.5, result.getLowerBound(), 0.0000001);
        assertEquals(7.5, result.getUpperBound(), 0.0000001);
    }

    @Test
    public void expandShouldIncreaseBoundsUsingMargins() {
        Range base = new Range(2.0, 6.0);

        Range result = Range.expand(base, 0.25, 0.50);

        assertEquals(1.0, result.getLowerBound(), 0.0000001);
        assertEquals(8.0, result.getUpperBound(), 0.0000001);
    }

    @Test
    public void shiftShouldPreventZeroCrossingWhenFlagIsFalse() {
        Range base = new Range(-2.0, 3.0);

        Range result = Range.shift(base, -4.0, false);

        assertEquals(-6.0, result.getLowerBound(), 0.0000001);
        assertEquals(0.0, result.getUpperBound(), 0.0000001);
    }

    @Test
    public void shiftShouldAllowZeroCrossingWhenFlagIsTrue() {
        Range base = new Range(-2.0, 3.0);

        Range result = Range.shift(base, -4.0, true);

        assertEquals(-6.0, result.getLowerBound(), 0.0000001);
        assertEquals(-1.0, result.getUpperBound(), 0.0000001);
    }
}
