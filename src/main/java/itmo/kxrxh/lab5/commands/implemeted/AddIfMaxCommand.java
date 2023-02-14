package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.commands.Executable;

public class AddIfMaxCommand extends CollectionDependent {
    public AddIfMaxCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {

    }
}
