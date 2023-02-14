package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.implemeted.*;
import itmo.kxrxh.lab5.utils.types.SizedStack;

/**
 * Command builder. Used to build command object.
 * <p>
 * Creates {@link Executable} command object from command name and command args.
 *
 * @author kxrxh
 * @see Executable
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
     * Build executable command.
     * Used to build command from command name and command args.<p>
     * Also adds command to history.<p>
     * If command is not found, returns {@link UnknownCommand}.
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
            case "save" -> new SaveCommand(collectionManager);
            // TODO
            case "add_if_max" -> new AddIfMaxCommand(collectionManager);
            case "update_id" -> new UpdateByIdCommand(collectionManager, commandArgs);
            case "remove_greater" -> new RemoveGreaterCommand(collectionManager, commandArgs);
            case "average_of_manufacture_cost" -> null;
            case "count_greater_than_manufacturer" ->
                    new CountGreaterThanManufacturerCommand(collectionManager, commandArgs);
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
