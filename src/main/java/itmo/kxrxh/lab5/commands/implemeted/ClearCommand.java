package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;

/**
 * Clear collection command
 *
 * @author kxrxh
 */
@CollectionEditor
public final class ClearCommand extends CollectionDependent {
    @Override
    public void execute() {
        collectionManager.clear();
    }
}
