package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.Executable;

/**
 * Exit command
 *
 * @author kxrxh
 */
public record ExitCommand() implements Executable {
    @Override
    public void execute() {
        System.exit(0);
    }
}
