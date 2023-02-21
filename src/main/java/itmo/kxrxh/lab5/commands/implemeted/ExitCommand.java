package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.CollectionCore;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.commands.CommandInvoker;
import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.terminal.Colors;

import java.util.Scanner;

/**
 * Exit command
 *
 * @author kxrxh
 */
public class ExitCommand implements Executable {
    private final int commandHistorySize = CommandInvoker.getHistorySize();
    private final CommandBuilder commandBuilder = CollectionCore.getCommandBuilder();

    @Override
    public void execute() {
        if (commandHistorySize >= 1) {
            System.out.println(Colors.ANSI_YELLOW + "Unsaved data can be lost!" + Colors.ANSI_RESET);
            System.out.print(Colors.ANSI_YELLOW + "Save the collection before exiting (y/n): " + Colors.ANSI_RESET);
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("y") || answer.equals("yes")) {
                try {
                    CollectionCore.getInvoker().execute(commandBuilder.buildCommand("save"));
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(Colors.ANSI_GREEN + "Exiting..." + Colors.ANSI_RESET);
        System.exit(0);
    }
}
