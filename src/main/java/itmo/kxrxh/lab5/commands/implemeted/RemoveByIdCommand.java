package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;

/**
 * Remove product by id command
 *
 * @author kxrxh
 */
@CollectionEditor
public final class RemoveByIdCommand extends CollectionDependent {
    private final long id;

    /**
     * Instantiates a new Remove by id command.
     *
     * @param commandArgs Command arguments
     */
    public RemoveByIdCommand(String[] commandArgs) {
        if (commandArgs.length == 0) {
            throw new IllegalArgumentException("No arguments");
        }
        id = Long.parseLong(commandArgs[0]);
    }

    @Override
    public void execute() {
        collectionManager.removeById(id);
    }
}
