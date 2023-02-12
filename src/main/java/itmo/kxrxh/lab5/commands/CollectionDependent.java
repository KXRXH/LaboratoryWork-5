package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;

/**
 * Abstract class for commands that depend on collection
 *
 * @author kxrxh
 */
public abstract class CollectionDependent implements Executable {
    /**
     * Collection manager
     *
     * @see CollectionManager
     */
    protected final CollectionManager collectionManager;

    /**
     * Instantiates a new Collection dependent.
     *
     * @param collectionManager Collection manager
     */
    public CollectionDependent(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

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
