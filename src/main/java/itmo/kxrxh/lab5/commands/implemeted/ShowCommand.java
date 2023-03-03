package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.commands.Command;

/**
 * Show command - prints all elements of collection
 *
 * @author kxrxh
 */
@Command(name = "Show")
public final class ShowCommand extends CollectionDependentCommand {
    @Override
    public void execute() {
        collectionManager.show();
    }
}
