import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.jfree.data.Range;

public class AiRangeTest {

    private Range range;

    @Before
    public void setUp() {
        range = new Range(10.0, 20.0);
    }

    @After
    public void tearDown() {
        range = null;
    }

    // -----------------------------
    // getLowerBound / getUpperBound
    // -----------------------------

    @Test
    public void testGetLowerBound() {
        assertEquals(10.0, range.getLowerBound(), 0.0001);
    }

    @Test
    public void testGetUpperBound() {
        assertEquals(20.0, range.getUpperBound(), 0.0001);
    }

    // -----------------------------
    // getLength
    // -----------------------------

    @Test
    public void testGetLength() {
        assertEquals(10.0, range.getLength(), 0.0001);
    }

    @Test
    public void testGetLengthZeroRange() {
        Range r = new Range(5.0, 5.0);
        assertEquals(0.0, r.getLength(), 0.0001);
    }

    // -----------------------------
    // getCentralValue
    // -----------------------------

    @Test
    public void testGetCentralValue() {
        assertEquals(15.0, range.getCentralValue(), 0.0001);
    }

    // -----------------------------
    // contains()
    // -----------------------------

    @Test
    public void testContainsInside() {
        assertTrue(range.contains(15.0));
    }

    @Test
    public void testContainsLowerBoundary() {
        assertTrue(range.contains(10.0));
    }

    @Test
    public void testContainsUpperBoundary() {
        assertTrue(range.contains(20.0));
    }

    @Test
    public void testContainsBelowRange() {
        assertFalse(range.contains(9.9));
    }

    @Test
    public void testContainsAboveRange() {
        assertFalse(range.contains(20.1));
    }

    // -----------------------------
    // intersects()
    // -----------------------------

    @Test
    public void testIntersectsOverlap() {
        assertTrue(range.intersects(15.0, 25.0));
    }

    @Test
    public void testIntersectsExactBoundary() {
        assertTrue(range.intersects(20.0, 30.0));
    }

    @Test
    public void testIntersectsNoOverlap() {
        assertFalse(range.intersects(21.0, 30.0));
    }

    @Test
    public void testIntersectsFullOverlap() {
        assertTrue(range.intersects(5.0, 25.0));
    }

    // -----------------------------
    // constrain()
    // -----------------------------

    @Test
    public void testConstrainInsideRange() {
        assertEquals(15.0, range.constrain(15.0), 0.0001);
    }

    @Test
    public void testConstrainBelowRange() {
        assertEquals(10.0, range.constrain(5.0), 0.0001);
    }

    @Test
    public void testConstrainAboveRange() {
        assertEquals(20.0, range.constrain(30.0), 0.0001);
    }

    // -----------------------------
    // expandToInclude()
    // -----------------------------

    @Test
    public void testExpandToIncludeInside() {
        Range r = Range.expandToInclude(range, 15.0);
        assertEquals(10.0, r.getLowerBound(), 0.0001);
        assertEquals(20.0, r.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeBelow() {
        Range r = Range.expandToInclude(range, 5.0);
        assertEquals(5.0, r.getLowerBound(), 0.0001);
        assertEquals(20.0, r.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeAbove() {
        Range r = Range.expandToInclude(range, 25.0);
        assertEquals(10.0, r.getLowerBound(), 0.0001);
        assertEquals(25.0, r.getUpperBound(), 0.0001);
    }

    // -----------------------------
    // expand()
    // -----------------------------

    @Test
    public void testExpandRange() {
        Range r = Range.expand(range, 0.1, 0.1);

        assertEquals(9.0, r.getLowerBound(), 0.0001);
        assertEquals(21.0, r.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandZeroMargins() {
        Range r = Range.expand(range, 0.0, 0.0);

        assertEquals(10.0, r.getLowerBound(), 0.0001);
        assertEquals(20.0, r.getUpperBound(), 0.0001);
    }

    // -----------------------------
    // shift()
    // -----------------------------

    @Test
    public void testShiftPositiveDelta() {
        Range r = Range.shift(range, 5.0, true);

        assertEquals(15.0, r.getLowerBound(), 0.0001);
        assertEquals(25.0, r.getUpperBound(), 0.0001);
    }

    @Test
    public void testShiftNegativeDelta() {
        Range r = Range.shift(range, -5.0, true);

        assertEquals(5.0, r.getLowerBound(), 0.0001);
        assertEquals(15.0, r.getUpperBound(), 0.0001);
    }

    @Test
    public void testShiftNoZeroCrossing() {
        Range r = new Range(1.0, 5.0);
        Range shifted = Range.shift(r, -3.0, false);

        assertEquals(0.0, shifted.getLowerBound(), 0.0001);
        assertEquals(2.0, shifted.getUpperBound(), 0.0001);
    }

}