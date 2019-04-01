package service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import org.junit.Test;

import domain.drawing.Canvas;
import domain.drawing.Line;
import domain.drawing.Rectangle;

public class CommandServiceTest {

    private CommandService commandService = new CommandService();

    private Scanner mockScanner(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        return new Scanner(System.in);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCanvas1() {
        Scanner scan = this.mockScanner("-10 2");
        commandService.createCanvas(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createCanvas2() {
        Scanner scan = this.mockScanner("10 -2");
        commandService.createCanvas(scan);
    }

    @Test
    public void createCanvas3() {
        Scanner scan = this.mockScanner("1 2 3 4");
        Canvas canvas = commandService.createCanvas(scan);
        assertNotNull(canvas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine1() {
        Scanner scan = this.mockScanner("-1 2 3 4");
        commandService.drawLine(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine2() {
        Scanner scan = this.mockScanner("1 -2 3 4");
        commandService.drawLine(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine3() {
        Scanner scan = this.mockScanner("1 2 -3 4");
        commandService.drawLine(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine4() {
        Scanner scan = this.mockScanner("1 2 3 -4");
        commandService.drawLine(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawLine4b() {
        Scanner scan = this.mockScanner("1 2 3 s");
        commandService.drawLine(scan);
    }

    @Test
    public void drawLine5() {
        Scanner scan = this.mockScanner("1 1 5 1");
        Line line = commandService.drawLine(scan);
        assertNotNull(line);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle1() {
        Scanner scan = this.mockScanner("-1 2 3 4");
        commandService.drawRectangle(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle2() {
        Scanner scan = this.mockScanner("1 -2 3 4");
        commandService.drawRectangle(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle3() {
        Scanner scan = this.mockScanner("1 2 -3 4");
        commandService.drawRectangle(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle4() {
        Scanner scan = this.mockScanner("1 2 3 -4");
        commandService.drawRectangle(scan);
    }

    @Test(expected = IllegalArgumentException.class)
    public void drawRectangle4b() {
        Scanner scan = this.mockScanner("1 2 3 s");
        commandService.drawRectangle(scan);
    }

    @Test
    public void drawRectangle5() {
        Scanner scan = this.mockScanner("1 1 5 1");
        Rectangle rectangle = commandService.drawRectangle(scan);
        assertNotNull(rectangle);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bucketFill1() {
        Scanner scan = this.mockScanner("-10 2 o");
        Canvas canvas = new Canvas(5, 5);
        commandService.bucketFill(scan, canvas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bucketFill2() {
        Scanner scan = this.mockScanner("10 -2 o");
        Canvas canvas = new Canvas(5, 5);
        commandService.bucketFill(scan, canvas);
    }

    @Test(expected = IllegalArgumentException.class)
    public void bucketFill3() {
        Scanner scan = this.mockScanner("-10 2 ooo");
        Canvas canvas = new Canvas(5, 5);
        commandService.bucketFill(scan, canvas);
    }

    @Test
    public void bucketFill4() {
        Scanner scan = this.mockScanner("2 2 o");
        Canvas canvas = new Canvas(5, 5);
        commandService.bucketFill(scan, canvas);
        assertNotNull(canvas);
    }

    @Test
    public void executeCommand() {

        Scanner scan = this.mockScanner("");
        assertTrue(commandService.executeCommand(scan));
        scan = this.mockScanner("Q");
        assertFalse(commandService.executeCommand(scan));
        scan = this.mockScanner("Q 1 2");
        assertFalse(commandService.executeCommand(scan));
        scan = this.mockScanner("C 20 4");
        assertTrue(commandService.executeCommand(scan));
        scan = this.mockScanner("L 1 2 6 2");
        assertTrue(commandService.executeCommand(scan));
        scan = this.mockScanner("L 6 3 6 4");
        assertTrue(commandService.executeCommand(scan));
        scan = this.mockScanner("R 14 1 18 3");
        assertTrue(commandService.executeCommand(scan));
        scan = this.mockScanner("B 10 3 o");
        assertTrue(commandService.executeCommand(scan));

    }

}
