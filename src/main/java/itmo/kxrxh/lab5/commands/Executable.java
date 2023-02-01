package itmo.kxrxh.lab5.commands;

public interface Executable {
    default void execute() {
        System.out.println("Unknown command. Type 'help' to see the list of available commands.");
    }
}
