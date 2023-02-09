package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.implemeted.*;
import itmo.kxrxh.lab5.utils.types.SizedStack;

/**
 * Command builder. Used to build command object.
 *
 * @author kxrxh
 */
public class CommandBuilder {

    private final CollectionManager collectionManager;

    private final SizedStack<String> history = new SizedStack<>(7);

    /**
     * Instantiates a new Command builder.
     *
     * @param collectionManager the collection manager
     */
    public CommandBuilder(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Build command executable.
     *
     * @param command     the command
     * @param commandArgs the command args
     * @return the executable
     */
    public Executable buildCommand(String command, String[] commandArgs) {
        // Add command to history
        history.push(command);
        return switch (command) {
            case "help" -> new HelpCommand();
            case "info" -> new InfoCommand(collectionManager);
            case "show" -> new ShowCommand(collectionManager);
            case "clear" -> new ClearCommand(collectionManager);
            case "head" -> new HeadCommand(collectionManager);
            case "sum_of_price" -> new SumOfPriceCommand(collectionManager);
            case "history" -> new HistoryCommand(history);
            case "exit" -> new ExitCommand();
            case "add" -> new AddCommand(collectionManager);
            case "execute_script" -> new ExecuteScriptCommand(collectionManager, commandArgs, this);
            case "remove_by_id" -> new RemoveByIdCommand(collectionManager, commandArgs);
            default -> new UnknownCommand();
        };
    }

    /**
     * Build command executable.
     *
     * @param line the line with command
     * @return the executable
     */
    public Executable buildCommand(String line) {
        String[] args = line.split(" ");
        String commandName = args[0];
        String[] commandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, commandArgs, 0, args.length - 1);
        return this.buildCommand(commandName, commandArgs);
    }
}
