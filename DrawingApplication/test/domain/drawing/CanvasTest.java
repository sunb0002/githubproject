package domain.drawing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class CanvasTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInitN1() {
        new Canvas(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitN2() {
        new Canvas(2, -1);
    }

    @Test
    public void testInitP1() {

        Canvas canvas = new Canvas(20, 4);
        assertNotNull(canvas);
        assertEquals(20, canvas.getWidth());
        assertEquals(4, canvas.getHeight());
        assertNull(canvas.getContent()[0][0]);
        assertNull(canvas.getContent()[3][19]);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillN1() {
        Canvas canvas = new Canvas(10, 4);
        assertNotNull(canvas);
        canvas.bucketFill('t', 20, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBucketFillN2() {
        Canvas canvas = new Canvas(10, 4);
        assertNotNull(canvas);
        canvas.bucketFill(null, 2, 2);
    }

    @Test
    public void testBucketFillP1() {
        Canvas canvas = new Canvas(20, 4);
        assertNotNull(canvas);

        Character color = 't';
        canvas.bucketFill(color, 4, 4);
        for (int y = 0; y < canvas.getHeight(); y++) {
            for (int x = 0; x < canvas.getWidth(); x++) {
                assertEquals(color, canvas.getContent()[y][x]);
            }
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddLayerN1() {
        Canvas canvas = new Canvas(2, 2);
        assertNotNull(canvas);
        Rectangle rect = new Rectangle(1, 1, 10, 10);
        assertNotNull(rect);
        canvas.addLayer(rect);
    }

    @Test
    public void testAddLayerP1() {
        Canvas canvas = new Canvas(20, 20);
        assertNotNull(canvas);
        Rectangle rect = new Rectangle(1, 1, 10, 10);
        assertNotNull(rect);

        canvas.addLayer(rect);

        assertNull(canvas.getContent()[1][1]);
        assertEquals(rect.getColor(), canvas.getContent()[0][0]);
        assertNull(canvas.getContent()[5][5]);
        assertEquals(rect.getColor(), canvas.getContent()[9][9]);

    }

}
