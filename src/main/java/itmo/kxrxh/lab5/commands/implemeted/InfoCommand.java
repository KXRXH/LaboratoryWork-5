package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.commands.Command;

/**
 * Info command - prints information about collection
 *
 * @author kxrxh
 */
@Command(name = "Info")
public final class InfoCommand extends CollectionDependentCommand {
    @Override
    public void execute() {
        collectionManager.getInfo();
    }
}
