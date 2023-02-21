package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependent;

/**
 * Info command - prints information about collection
 *
 * @author kxrxh
 */
public final class InfoCommand extends CollectionDependent {
    @Override
    public void execute() {
        collectionManager.getInfo();
    }
}
