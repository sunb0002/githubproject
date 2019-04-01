package domain.drawing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class LineTest {

    @Test(expected = IllegalArgumentException.class)
    public void testN1() {
        new Line(1, 2, 3, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testN2() {
        new Line(3, 3, 1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testN3() {
        new Line(-2, 1, -2, 4);
    }

    @Test
    public void testP1() {

        Line line = new Line(1, 2, 6, 2);
        assertNotNull(line);
        assertEquals(6, line.getWidth());
        assertEquals(2, line.getHeight());

        assertNull(line.getContent()[0][0]);
        assertNull(line.getContent()[0][5]);
        assertEquals(line.getColor(), line.getContent()[1][0]);
        assertEquals(line.getColor(), line.getContent()[1][5]);

    }

}
