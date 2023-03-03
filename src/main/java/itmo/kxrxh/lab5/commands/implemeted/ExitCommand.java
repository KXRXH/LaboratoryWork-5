package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.CollectionCore;
import itmo.kxrxh.lab5.commands.Command;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;
import itmo.kxrxh.lab5.utils.terminal.Colors;
import itmo.kxrxh.lab5.utils.types.SizedStack;

import java.util.Scanner;

/**
 * Exit command
 *
 * @author kxrxh
 */
@Command(name = "Exit")
public class ExitCommand implements Executable {
    private final SizedStack<Executable> commandHistory;
    private final CommandBuilder commandBuilder = CollectionCore.getCommandBuilder();

    public ExitCommand(SizedStack<Executable> commandHistory) {
        this.commandHistory = commandHistory;
    }

    @Override
    public void execute() {
        if (commandHistory.stream().anyMatch(c -> c.getClass().isAnnotationPresent(CollectionEditor.class))) {
            System.out.println(Colors.AsciiYellow + "Unsaved data can be lost!" + Colors.AsciiReset);
            System.out.print(Colors.AsciiYellow + "Save the collection before exiting (y/n): " + Colors.AsciiReset);
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
        System.out.println(Colors.AsciiGreen + "Exiting..." + Colors.AsciiReset);
        System.exit(0);
    }
}
