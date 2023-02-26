package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;
import itmo.kxrxh.lab5.utils.parser.Parser;

@CollectionEditor
public class AddIfMaxCommand extends CollectionDependentCommand {
    @Override
    public void execute() {
        Product product = Parser.readObject(Product.class);
        if (product != null) {
            collectionManager.addIfMax(product);
        }
    }
}
