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
        System.out.println(Colors.ANSI_YELLOW + "Unknown command. Type 'help' to see the list of available commands." + Colors.ANSI_RESET);
    }
}
