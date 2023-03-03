package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.commands.implemeted.*;

import static itmo.kxrxh.lab5.commands.CommandInvoker.getExecuteHistory;


/**
 * Command builder. Used to build command object.
 * <p>
 * Creates {@link Executable} command object from command name and command args.
 *
 * @author kxrxh
 * @see Executable
 */
public class CommandBuilder {
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
        return switch (command) {
            case "help" -> new HelpCommand();
            case "info" -> new InfoCommand();
            case "show" -> new ShowCommand();
            case "clear" -> new ClearCommand();
            case "head" -> new HeadCommand();
            case "sum_of_price" -> new SumOfPriceCommand();
            case "history" -> new HistoryCommand(getExecuteHistory());
            case "exit" -> new ExitCommand(getExecuteHistory());
            case "add" -> new AddCommand();
            case "execute_script" -> {
                Executable executable = null;
                try {
                    executable = new ExecuteScriptCommand(commandArgs);
                } catch (Exception e) {
                    System.err.println("\n" + e.getMessage());
                }
                yield executable;
            }
            case "remove_by_id" -> new RemoveByIdCommand(commandArgs);
            case "save" -> new SaveCommand();
            case "add_if_max" -> new AddIfMaxCommand();
            case "update_id" -> new UpdateByIdCommand(commandArgs);
            case "remove_greater" -> new RemoveGreaterCommand();
            case "average_of_manufacture_cost" -> new AverageOfManufactureCostCommand();
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
