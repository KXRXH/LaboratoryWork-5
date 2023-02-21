package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.types.Product;

public class AverageOfManufactureCostCommand extends CollectionDependent {
    @Override
    public void execute() {
        double sum = 0;
        for (Product product : collectionManager.collection()) {
            sum += product.getManufactureCost();
        }
        System.out.println(sum / collectionManager.collection().size());
    }
}
