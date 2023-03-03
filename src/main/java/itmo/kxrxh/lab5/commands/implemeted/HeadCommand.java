package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.commands.Command;
import itmo.kxrxh.lab5.types.Product;

/**
 * Head command - returns first element of collection
 *
 * @author kxrxh
 */
@Command(name = "Head")
public final class HeadCommand extends CollectionDependentCommand {
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
