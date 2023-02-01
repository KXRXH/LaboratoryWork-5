package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;

public class SumOfPriceCommand extends CollectionDependent {
    public SumOfPriceCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        System.out.println(getCollectionManager().sumOfPrice());
    }
}
