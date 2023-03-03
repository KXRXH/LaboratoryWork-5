package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.commands.Command;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;

/**
 * Remove product by id command
 *
 * @author kxrxh
 */
@Command(name = "RemoveById")
@CollectionEditor
public final class RemoveByIdCommand extends CollectionDependentCommand {
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
        try {
            id = Long.parseLong(commandArgs[0]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid id value");
        }
    }

    @Override
    public void execute() {
        if (collectionManager.collectionContainsItemWithId(id)) {
            collectionManager.removeById(id);
            return;
        }
        throw new RuntimeException("No such item with given id: " + id);
    }
}
