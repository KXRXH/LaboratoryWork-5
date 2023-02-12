package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.utils.Terminal.Colors;

/**
 * Interface for all executable commands
 *
 * @author kxrxh
 */
public interface Executable {
    /**
     * Execute command
     */
    default void execute() {
        System.out.println("\u001B[33mUnknown command. Type 'help' to see the list of available commands.\u001B[0m");
    }

    /**
     * Return command name in string format
     *
     * @return Command name
     */
    default String getCommandName() {
        return "Unknown";
    }
}
