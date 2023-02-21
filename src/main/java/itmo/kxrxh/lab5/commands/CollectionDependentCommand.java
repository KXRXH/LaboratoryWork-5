package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.collection.CollectionCore;
import itmo.kxrxh.lab5.collection.manager.CollectionManager;

/**
 * Abstract class for commands that depend on collection
 *
 * @author kxrxh
 */
public abstract class CollectionDependentCommand implements Executable {
    /**
     * Collection manager
     *
     * @see CollectionManager
     */
    protected final CollectionManager collectionManager = CollectionCore.getCollectionManager();

    @Override
    public void execute() {
    }

    /**
     * Getter for collection manager
     *
     * @return Collection manager
     */
    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
