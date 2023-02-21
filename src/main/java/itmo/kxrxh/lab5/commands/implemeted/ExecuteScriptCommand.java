package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.CollectionCore;
import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.commands.Executable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Execute script command.
 *
 * @author kxrxh
 */
public class ExecuteScriptCommand extends CollectionDependent {

    protected static final List<Executable> fileHistory = new ArrayList<>();
    protected final String[] commandArgs;
    private final CommandBuilder commandBuilder = CollectionCore.getCommandBuilder();

    /**
     * Instantiates a new Execute script command.
     *
     * @param commandArgs the command args
     * @see CommandBuilder
     * @see CollectionManager
     */
    public ExecuteScriptCommand(String[] commandArgs) throws Exception {
        this.commandArgs = commandArgs;
        if (fileHistory.contains(this)) {
            throw new Exception("Script recursion detected");
        } else {
            fileHistory.add(this);
        }
    }

    /**
     * Read file and execute commands.
     *
     * @param fileName Name of file to read
     */
    private void readFile(String fileName) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                Executable executable = commandBuilder.buildCommand(line);
                if (executable == null) {
                    continue;
                }
                executable.execute();
                fileHistory.remove(this);
            }
        } catch (IOException e) {
            System.err.println("Error reading script file: " + fileName);
        }
    }


    @Override
    public void execute() {
        if (commandArgs.length == 0) {
            throw new RuntimeException("Script file name not specified");
        }
        readFile(commandArgs[0]);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExecuteScriptCommand) {
            return Arrays.equals(commandArgs, ((ExecuteScriptCommand) obj).commandArgs);
        }
        return false;
    }
}
