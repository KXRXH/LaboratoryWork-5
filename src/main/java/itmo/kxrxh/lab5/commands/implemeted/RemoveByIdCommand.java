package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;

/**
 * Remove product by id command
 *
 * @author kxrxh
 */
@CollectionEditor
public final class RemoveByIdCommand extends CollectionDependentCommand {
    private final long id;

    /**
     * Instantiates a new Remove by id command.
     *
     * @param commandArgs Command arguments
     */
    // FIXME: fix parse error
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
