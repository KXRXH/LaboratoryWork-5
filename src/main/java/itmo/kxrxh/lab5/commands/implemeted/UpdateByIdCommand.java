package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;
import itmo.kxrxh.lab5.utils.parser.Parser;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

@CollectionEditor
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
                    long offset = getFieldOffset(Product.class, "id");
                    Unsafe unsafe = getUnsafe();
                    unsafe.putObject(product, offset, id);
                } catch (IllegalAccessException illegalAccessException) {
                    throw new RuntimeException("Unable to access id field", illegalAccessException);
                } catch (Exception noSuchFieldException) {
                    throw new RuntimeException("Unable to find id field", noSuchFieldException);
                }
                collectionManager.updateItem(product);
                return;
            }
        }
        throw new RuntimeException("No such item with given id: " + id);
    }

    private static long getFieldOffset(Class<?> clazz, String fieldName) throws Exception {
        Field field = clazz.getDeclaredField(fieldName);
        return getUnsafe().objectFieldOffset(field);
    }

    private static Unsafe getUnsafe() throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }
}
