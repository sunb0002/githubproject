package domain.drawing;

import constant.Config.CanvasColor;

public class Rectangle extends DrawingTool {

    public Rectangle(int x1, int y1, int x2, int y2) {
        super();
        if (x1 > x2 || y1 > y2) {
            throw new IllegalArgumentException("Please draw the line from upper-left to lower-right.");
        }
        if (x1 < 1 || y1 < 1) {
            throw new IllegalArgumentException("Invalid starting point.");
        }
        this.init(x1, y1, x2, y2);
    }

    @Override
    public Character getColor() {
        return CanvasColor.RECTANGLE;
    }

    @Override
    protected void init(int x1, int y1, int x2, int y2) {

        this.content = new Character[y2][x2];

        for (int x = x1; x <= x2; x++) {
            this.content[y1 - 1][x - 1] = this.getColor();
            this.content[y2 - 1][x - 1] = this.getColor();
        }

        for (int y = y1; y <= y2; y++) {
            this.content[y - 1][x1 - 1] = this.getColor();
            this.content[y - 1][x2 - 1] = this.getColor();
        }
    }

}
