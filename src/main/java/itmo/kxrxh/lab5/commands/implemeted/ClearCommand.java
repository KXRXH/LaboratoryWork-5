package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;

public class ClearCommand extends CollectionDependent {

    public ClearCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        getCollectionManager().clear();
    }
}
