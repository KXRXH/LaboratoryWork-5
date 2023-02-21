package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependent;

/**
 * Show command - prints all elements of collection
 *
 * @author kxrxh
 */
public final class ShowCommand extends CollectionDependent {
    @Override
    public void execute() {
        collectionManager.show();
    }
}
