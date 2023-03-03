package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.Command;
import itmo.kxrxh.lab5.commands.Executable;

/**
 * Unknown command. Used when command is not found
 *
 * @author kxrxh
 */
@Command()
public record UnknownCommand() implements Executable {
}
