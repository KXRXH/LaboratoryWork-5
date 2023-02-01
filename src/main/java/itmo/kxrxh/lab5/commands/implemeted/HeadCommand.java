package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.types.Product;

/**
 * Head command - returns first element of collection
 *
 * @author kxrxh
 */
public final class HeadCommand extends CollectionDependent {
    /**
     * Instantiates a new Head command.
     *
     * @param collectionManager collection manager
     * @see CollectionManager
     */
    public HeadCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        Product head = getCollectionManager().head();
        if (head == null) {
            System.out.println("Collection is empty");
            return;
        }
        System.out.println(getCollectionManager().head());
    }
}
