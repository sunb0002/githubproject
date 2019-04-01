import java.util.Scanner;

import service.CommandService;
import utils.ConsoleUtil;

/**
 * 
 * @author sunbo
 *
 */
public class DrawingApplication {

    public static void main(String[] args) {

        ConsoleUtil.printLine("==== DrawingApplication started! ====");
        ConsoleUtil.newLine();

        CommandService commandService = new CommandService();

        Scanner scan = new Scanner(System.in);
        while (commandService.executeCommand(scan)) {
        }
        scan.close();

        ConsoleUtil.newLine();
        ConsoleUtil.printLine("==== DrawingApplication finished. ====");
    }

}
