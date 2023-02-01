package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;

public abstract class CollectionDependent implements Executable {
    private final CollectionManager collectionManager;

    public CollectionDependent(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {

    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }
}
