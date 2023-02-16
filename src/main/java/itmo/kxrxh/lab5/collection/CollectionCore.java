package itmo.kxrxh.lab5.collection;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.terminal.Colors;
import itmo.kxrxh.lab5.utils.xml.XML;
import itmo.kxrxh.lab5.utils.xml.XMLReader;
import itmo.kxrxh.lab5.utils.xml.exceptions.UnableToCreateFileException;

import java.util.NoSuchElementException;
import java.util.Scanner;

import static itmo.kxrxh.lab5.Main.ENVIRONMENT_VARIABLE;
import static java.lang.System.exit;


public class CollectionCore {
    private CollectionManager collectionManager;
    private CommandBuilder commandBuilder;

    private XML xmlCore;
    public static final String buildersPath = "itmo.kxrxh.lab5.types.builders";

    private static final DotEnv dotEnv;

    static {
        dotEnv = new DotEnv(".env");
        // Reading .env file
        dotEnv.load();
    }

    public CollectionCore() {
    }

    public CollectionCore init() {
        ProductCollector productCollector = new ProductCollector();
        collectionManager = new CollectionManager(productCollector);
        commandBuilder = new CommandBuilder(collectionManager);
        try {
            xmlCore = new XML(dotEnv.get(ENVIRONMENT_VARIABLE));
        } catch (UnableToCreateFileException | NoSuchFieldException e) {
            System.err.println(e.getMessage());
            exit(1);
        }
        return this;
    }

    public CollectionCore fillOutCollection() {
        XMLReader xmlReader;
        xmlReader = xmlCore.newReader();
        // Parsing collection from file
        System.out.println(Colors.ANSI_YELLOW + "Reading collection from file..." + Colors.ANSI_RESET);
        ProductCollector parsedProductCollector;
        try {
            parsedProductCollector = xmlReader.parse();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        collectionManager.collection().addAll(parsedProductCollector);
        System.out.println(Colors.ANSI_GREEN + "Collection was successfully read from file." + Colors.ANSI_RESET);
        return this;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        // Program loop
        while (true) {
            System.out.print("\u001B[35m>> \u001B[37m");
            // Reading user input
            String userInput;
            try {
                userInput = in.nextLine();
            } catch (NoSuchElementException e) {
                System.err.println("EOF");
                return;
            }
            System.out.print(Colors.ANSI_RESET);
            try {
                commandBuilder.buildCommand(userInput).execute();
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

