package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;
import itmo.kxrxh.lab5.utils.parser.Parser;

/**
 * Add command.
 *
 * @author kxrxh
 */
@CollectionEditor
public final class AddCommand extends CollectionDependentCommand {
    @Override
    public void execute() {
        Product product = Parser.readObject(Product.class);
        if (product != null) {
            collectionManager.add(product);
            System.out.println("\u001B[32mProduct was successfully added to collection.\u001B[0m");
        }
    }
}
