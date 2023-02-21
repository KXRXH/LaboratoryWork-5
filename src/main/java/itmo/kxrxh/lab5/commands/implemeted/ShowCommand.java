package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;

/**
 * Show command - prints all elements of collection
 *
 * @author kxrxh
 */
public final class ShowCommand extends CollectionDependentCommand {
    @Override
    public void execute() {
        collectionManager.show();
    }
}
