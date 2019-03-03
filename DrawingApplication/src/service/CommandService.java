package service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import domain.command.Command;
import domain.drawing.Canvas;
import domain.drawing.Line;
import domain.drawing.Rectangle;
import utils.ConsoleUtil;

public class CommandService {

    private static final Map<String, Command> commands;
    private Canvas canvas;

    static {
        commands = new HashMap<>();
        for (Command cmd : Command.values()) {
            commands.put(cmd.getValue(), cmd);
        }
    }

    /**
     * 
     * @param scan
     * @return
     */
    public boolean executeCommand(final Scanner scan) {

        ConsoleUtil.print("enter command: ");

        try {
            Command cmd = this.getCommandByInput(this.getNextStr(scan));

            switch (cmd) {
            case INVALID:
                throw new IllegalArgumentException("Invalid command.");
            case QUIT:
                return false;
            case CREATE_CANVAS:
                this.canvas = this.createCanvas(scan);
                break;
            case DRAW_LINE:
                this.validateCanvas();
                this.canvas.addLayer(this.drawLine(scan));
                break;
            case DRAW_RECTANGLE:
                this.validateCanvas();
                this.canvas.addLayer(this.drawRectangle(scan));
                break;
            case BUCKET_FILL:
                this.validateCanvas();
                this.bucketFill(scan, this.canvas);
                break;
            default:
                break;
            }

            this.canvas.toPrint();
            return true;

        } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
            ConsoleUtil.printLine(
                    (e.getMessage() == null ? "General Exception occured." : e.getMessage()) + " Please enter again.");
            return true;
        } catch (Exception e) {
            ConsoleUtil.printLine("Fatal exception occured, exiting program.");
            e.printStackTrace();
            return false;
        } finally {
            if (scan.hasNextLine()) {
                scan.nextLine();
            }
        }

    }

    public Canvas createCanvas(final Scanner scan) {
        int width = this.getNextInt(scan);
        int height = this.getNextInt(scan);
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Invalid Canvas dimensions.");
        }
        return new Canvas(width, height);
    }

    public Line drawLine(final Scanner scan) {
        int x1 = this.getNextInt(scan);
        int y1 = this.getNextInt(scan);
        int x2 = this.getNextInt(scan);
        int y2 = this.getNextInt(scan);
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0) {
            throw new IllegalArgumentException("Invalid Line dimensions.");
        }
        return new Line(x1, y1, x2, y2);
    }

    public Rectangle drawRectangle(final Scanner scan) {
        int x1 = this.getNextInt(scan);
        int y1 = this.getNextInt(scan);
        int x2 = this.getNextInt(scan);
        int y2 = this.getNextInt(scan);
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0) {
            throw new IllegalArgumentException("Invalid Rectangle dimensions.");
        }
        return new Rectangle(x1, y1, x2, y2);
    }

    public void bucketFill(final Scanner scan, final Canvas canvas) {
        int x1 = this.getNextInt(scan);
        int y1 = this.getNextInt(scan);
        String colorStr = this.getNextStr(scan);
        if (x1 < 0 || y1 < 0 || colorStr == null || colorStr.length() > 1) {
            throw new IllegalArgumentException("Invalid Line dimensions.");
        }
        canvas.bucketFill(colorStr.charAt(0), x1, y1);
    }

    private void validateCanvas() {
        if (this.canvas == null) {
            throw new IllegalStateException("Canvas must be created first.");
        }
    }

    private Command getCommandByInput(String input) {
        return input == null ? Command.INVALID : commands.getOrDefault(input, Command.INVALID);
    }

    private String getNextStr(final Scanner scan) {
        return scan.hasNext() ? scan.next() : null;
    }

    private int getNextInt(final Scanner scan) {
        try {
            return scan.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }

}
