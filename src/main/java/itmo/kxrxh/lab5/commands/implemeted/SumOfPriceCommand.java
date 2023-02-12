package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;

/**
 * SumOfPrice command - returns sum of price of all elements of collection
 *
 * @author kxrxh
 */
public final class SumOfPriceCommand extends CollectionDependent {
    /**
     * Instantiates a new Sum of price command.
     *
     * @param collectionManager collection manager
     * @see CollectionManager
     */
    public SumOfPriceCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        System.out.println(collectionManager.sumOfPrice());
    }
}
