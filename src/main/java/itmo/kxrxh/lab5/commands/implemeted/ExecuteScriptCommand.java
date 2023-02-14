package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.commands.Executable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * Execute script command.
 *
 * @author kxrxh
 */
public class ExecuteScriptCommand extends CollectionDependent {

    private final CommandBuilder commandBuilder;
    protected final String[] commandArgs;

    /**
     * Instantiates a new Execute script command.
     *
     * @param collectionManager the collection manager
     * @param commandArgs       the command args
     * @param commandBuilder    the command builder
     * @see CommandBuilder
     * @see CollectionManager
     */
    public ExecuteScriptCommand(CollectionManager collectionManager, String[] commandArgs, CommandBuilder commandBuilder) {
        super(collectionManager);
        this.commandArgs = commandArgs;
        this.commandBuilder = commandBuilder;
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
                if (executable instanceof ExecuteScriptCommand) {
                    if (Arrays.equals(((ExecuteScriptCommand) executable).commandArgs, commandArgs)) {
                        System.out.println("\u001B[33mRecursion detected. Skipping script execution.\u001B[0m");
                        continue;
                    }
                }
                executable.execute();
            }
        } catch (IOException e) {
            System.out.println("Error reading script file: " + fileName);
        }
    }


    @Override
    public void execute() {
        if (commandArgs.length == 0) {
            throw new RuntimeException("Script file name not specified");
        }
        readFile(commandArgs[0]);
    }
}
