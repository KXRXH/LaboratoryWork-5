package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;

/**
 * Show command - prints all elements of collection
 *
 * @author kxrxh
 */
public final class ShowCommand extends CollectionDependent {

    /**
     * Instantiates a new Show command.
     *
     * @param collectionManager collection manager
     */
    public ShowCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        getCollectionManager().show();
    }
}
