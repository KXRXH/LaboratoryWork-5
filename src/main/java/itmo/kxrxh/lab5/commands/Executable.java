package itmo.kxrxh.lab5.commands;

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
}
