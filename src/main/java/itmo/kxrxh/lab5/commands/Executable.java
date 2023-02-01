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
        System.out.println("Unknown command. Type 'help' to see the list of available commands.");
    }
}
