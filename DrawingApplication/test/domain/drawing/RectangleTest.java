package domain.drawing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class RectangleTest {

    @Test(expected = IllegalArgumentException.class)
    public void testN1() {
        new Rectangle(-1, -2, 1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testN2() {
        new Rectangle(3, 3, 1, 1);
    }

    @Test
    public void testP1() {

        Rectangle rect = new Rectangle(14, 1, 18, 3);
        assertNotNull(rect);
        assertEquals(18, rect.getWidth());
        assertEquals(3, rect.getHeight());

        assertNull(rect.getContent()[0][0]);
        assertEquals(rect.getColor(), rect.getContent()[0][13]);
        assertEquals(rect.getColor(), rect.getContent()[2][17]);
        assertNull(rect.getContent()[1][14]);
        assertNull(rect.getContent()[1][15]);

    }

}
