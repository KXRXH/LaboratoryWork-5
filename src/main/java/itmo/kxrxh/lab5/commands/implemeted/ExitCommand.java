package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.Executable;

public record ExitCommand() implements Executable {
    @Override
    public void execute() {
        System.exit(0);
    }
}
