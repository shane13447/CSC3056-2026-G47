import static org.junit.Assert.assertEquals;

import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

public class AitestingGetCentralValue {

    private Range range;

    @Before
    public void setUp() {
        // Create a Range from -1 to 1
        range = new Range(-1.0, 1.0);
    }

    @Test
    public void testGetCentralValue() {
        // The central value of -1 to 1 should be 0
        double centralValue = range.getCentralValue();
        assertEquals(0.0, centralValue, 0.0000001);
    }
}