package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;

/**
 * SumOfPrice command - returns sum of price of all elements of collection
 *
 * @author kxrxh
 */
public final class SumOfPriceCommand extends CollectionDependentCommand {
    @Override
    public void execute() {
        System.out.println(collectionManager.sumOfPrice());
    }
}
