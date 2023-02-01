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
        switch (command) {
            case "help":
                return new HelpCommand();
            case "info":
                return new InfoCommand(collectionManager);
            case "show":
                return new ShowCommand(collectionManager);
            case "clear":
                return new ClearCommand(collectionManager);
            case "head":
                return new HeadCommand(collectionManager);
            case "sum_of_price":
                return new SumOfPriceCommand(collectionManager);
            case "history":
                return new HistoryCommand(history);
            case "exit":
                return new ExitCommand();
            case "execute_script": {
                return new ExecuteScriptCommand(collectionManager, commandArgs, this);
            }
            case "remove_by_id":
                return new RemoveByIdCommand(collectionManager, commandArgs);
            default:
                return new UnknownCommand();
        }
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
