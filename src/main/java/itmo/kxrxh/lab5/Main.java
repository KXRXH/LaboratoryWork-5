package itmo.kxrxh.lab5;


import itmo.kxrxh.lab5.collection.ModLinkedList;
import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.utils.dotenv.DotEnv;

import java.util.Scanner;


/**
 * Main class
 *
 * @author kxrxh
 * @version 369491
 */
public final class Main {
    // Initializing collection
    private static final ModLinkedList modLinkedList = new ModLinkedList();

    // Initializing .env file (for reading environment variables (Windows))
    // For reading environment variables (Linux) use EnvReader.get(KEY)
    private static final DotEnv dotEnv;

    static {
        dotEnv = new DotEnv(".env");
        // Loading .env file
        dotEnv.load();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        CollectionManager collectionManager = new CollectionManager(modLinkedList);
        // Reading .env file
        System.out.println(dotEnv.get("FILE_NAME"));
        CommandBuilder commandBuilder = new CommandBuilder(collectionManager);
        // Program loop
        while (true) {
            // Reading user input
            System.out.print(">> ");
            String userInput = in.nextLine();
            commandBuilder.buildCommand(userInput).execute();
        }
    }
}
