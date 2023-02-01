package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.commands.Executable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Execute script command.
 *
 * @author kxrxh
 */
public class ExecuteScriptCommand extends CollectionDependent {

    private final CommandBuilder commandBuilder;
    private final String[] commandArgs;

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
        ArrayList<String> lines = new ArrayList<>();
        Scanner scanner;

        // Read file
        File scriptFile = new File(fileName);
        try {
            scanner = new Scanner(scriptFile);
        } catch (FileNotFoundException e) {
            System.out.println("No such script file: " + fileName);
            return;
        }
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        // Execute commands
        for (String line : lines) {
            // Getting command object
            Executable executable = commandBuilder.buildCommand(line);
            executable.execute();
        }
        scanner.close();
    }

    @Override
    public void execute() {
        if (commandArgs.length == 0) {
            throw new RuntimeException("script file name not specified");
        }
        readFile(commandArgs[0]);
    }
}
