package domain.drawing;

public class Canvas extends DrawingTool {

    public Canvas(int width, int height) {
        super();
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Unable to create canvas with illegal dimensions.");
        }
        this.init(0, 0, width, height);
    }

    @Override
    public Character getColor() {
        return null;
    }

    @Override
    protected void init(int x1, int y1, int x2, int y2) {
        this.content = new Character[y2][x2];
    }

    /**
     * 
     * Filling the empty spaces on the canvas. 
     * @param color
     * @param x1
     * @param y1
     */
    public void bucketFill(Character color, int x1, int y1) {
        if (y1 > this.getHeight() || x1 > this.getWidth()) {
            throw new IllegalArgumentException("Unable to start filling from given coordinate.");
        }
        if (color == null) {
            throw new IllegalArgumentException("Unable to fill with empty color.");
        }
        this.fill(color, x1 - 1, y1 - 1);
    }

    private void fill(Character color, int x, int y) {

        if (y >= this.getHeight() || x == this.getWidth() || y < 0 || x < 0 || this.content[y][x] != null) {
            return;
        }

        this.content[y][x] = color;
        fill(color, x - 1, y);
        fill(color, x + 1, y);
        fill(color, x, y - 1);
        fill(color, x, y + 1);

    }

}
