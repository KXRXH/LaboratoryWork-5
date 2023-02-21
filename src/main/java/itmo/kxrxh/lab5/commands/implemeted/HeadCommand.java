package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.types.Product;

/**
 * Head command - returns first element of collection
 *
 * @author kxrxh
 */
public final class HeadCommand extends CollectionDependent {
    @Override
    public void execute() {
        Product head = collectionManager.head();
        if (head == null) {
            System.out.println("Collection is empty");
            return;
        }
        System.out.println(collectionManager.head());
    }
}
