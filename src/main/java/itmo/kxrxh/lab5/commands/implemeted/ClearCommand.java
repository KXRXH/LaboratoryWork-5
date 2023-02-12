package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;

/**
 * Clear collection command
 *
 * @author kxrxh
 */
public final class ClearCommand extends CollectionDependent {

    /**
     * Instantiates a new Clear command.
     *
     * @param collectionManager collection manager
     */
    public ClearCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        collectionManager.clear();
    }
}
