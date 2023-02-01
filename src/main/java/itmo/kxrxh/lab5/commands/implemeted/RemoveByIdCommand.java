package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;

/**
 * Remove product by id command
 *
 * @author kxrxh
 */
public final class RemoveByIdCommand extends CollectionDependent {
    private final long id;

    /**
     * Instantiates a new Remove by id command.
     *
     * @param collectionManager Collection manager
     * @param commandArgs       Command arguments
     * @see CollectionManager
     */
    public RemoveByIdCommand(CollectionManager collectionManager, String[] commandArgs) {
        super(collectionManager);
        if (commandArgs.length == 0) {
            throw new IllegalArgumentException("No arguments");
        }
        id = Long.parseLong(commandArgs[0]);
    }

    @Override
    public void execute() {
        getCollectionManager().removeById(id);
    }
}
