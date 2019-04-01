package domain.drawing;

import utils.ConsoleUtil;

public abstract class DrawingTool {

    protected Character[][] content;

    protected abstract Character getColor();

    protected abstract void init(int x1, int y1, int x2, int y2);

    public Character[][] getContent() {
        return this.content;
    }

    public int getHeight() {
        return this.content == null ? 0 : this.content.length;
    }

    public int getWidth() {
        return this.getHeight() < 1 ? 0 : this.content[0].length;
    }

    /**
     * 
     * Overlaying current content with new content
     */
    public void addLayer(final DrawingTool anotherLayer) {

        if (this.getHeight() < anotherLayer.getHeight() || this.getWidth() < anotherLayer.getWidth()) {
            throw new IllegalArgumentException("Unable to draw because it's out of boundary.");
        }

        for (int y = 0; y < anotherLayer.getHeight(); y++) {
            for (int x = 0; x < anotherLayer.getWidth(); x++) {
                Character c = anotherLayer.getContent()[y][x];
                if (c != null) {
                    this.content[y][x] = c;
                }
            }
        }

    }

    public void toPrint() {

        if (this.content == null) {
            throw new IllegalStateException("Drawing tool this not initialized properly.");
        }

        final String BORADER_COLOR_X = "-";
        final String BORADER_COLOR_Y = "|";

        for (int y = 0; y < this.getHeight() + 2; y++) {
            for (int x = 0; x < this.getWidth() + 2; x++) {
                if (y == 0 || y == this.getHeight() + 1) {
                    ConsoleUtil.print(BORADER_COLOR_X);
                } else if (x == 0 || x == this.getWidth() + 1) {
                    ConsoleUtil.print(BORADER_COLOR_Y);
                } else {
                    final Character c = this.content[y - 1][x - 1];
                    ConsoleUtil.print(c == null ? " " : c.toString());
                }
            }
            ConsoleUtil.newLine();
        }

    }

}
