package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;

/**
 * Info command - prints information about collection
 *
 * @author kxrxh
 */
public final class InfoCommand extends CollectionDependent {

    /**
     * Instantiates a new Info command.
     *
     * @param collectionManager collection manager
     */
    public InfoCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        getCollectionManager().getInfo();
    }
}
