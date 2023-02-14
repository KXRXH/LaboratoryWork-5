package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;

public class RemoveGreaterCommand extends CollectionDependent {
    public RemoveGreaterCommand(CollectionManager collectionManager, String[] commandArgs) {
        super(collectionManager);
    }

    @Override
    public void execute() {

    }
}
