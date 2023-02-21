package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.utils.TempFiles;
import itmo.kxrxh.lab5.utils.annotations.CollectionEditor;
import itmo.kxrxh.lab5.utils.types.SizedStack;

public final class CommandInvoker {

    private static final SizedStack<String> executeHistory = new SizedStack<>(7);

    public CommandInvoker() {
    }

    static SizedStack<String> getExecuteHistory() {
        return executeHistory;
    }
    public static int getHistorySize() {
        return executeHistory.size();
    }

    public void execute(Executable executable) throws NoSuchFieldException {
        getExecuteHistory().push(executable.getClass().getSimpleName());
        executable.execute();
        if (executable.getClass().isAnnotationPresent(CollectionEditor.class)) {
            TempFiles.createTempFile();
        }
    }
}
