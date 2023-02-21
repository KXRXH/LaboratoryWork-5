package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;

/**
 * Info command - prints information about collection
 *
 * @author kxrxh
 */
public final class InfoCommand extends CollectionDependentCommand {
    @Override
    public void execute() {
        collectionManager.getInfo();
    }
}
