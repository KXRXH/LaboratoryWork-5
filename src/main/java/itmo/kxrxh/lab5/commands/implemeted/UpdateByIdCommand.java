package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.utils.parser.Parser;

import java.lang.reflect.Field;

public class UpdateByIdCommand extends CollectionDependentCommand {
    private final long id;

    public UpdateByIdCommand(String[] commandArgs) {
        if (commandArgs.length == 0) {
            throw new IllegalArgumentException("No arguments");
        }
        try {
            id = Long.parseLong(commandArgs[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid id value");
        }
    }

    @Override
    public void execute() {
        if (collectionManager.collectionContainsItemWithId(id)) {
            Product product = Parser.readObject(Product.class);
            if (product != null) {
                try {
                    Field idField = Product.class.getDeclaredField("id");
                    idField.setAccessible(true);
                    idField.set(product, id);
                } catch (IllegalAccessException illegalAccessException) {
                    throw new RuntimeException("Unable to access id field", illegalAccessException);
                } catch (NoSuchFieldException noSuchFieldException) {
                    throw new RuntimeException("Unable to find id field", noSuchFieldException);
                }
                collectionManager.updateItem(product);
                return;
            }
        }
        throw new RuntimeException("No such item with given id: " + id);
    }
}
