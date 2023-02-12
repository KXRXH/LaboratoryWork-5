package itmo.kxrxh.lab5.collection;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.utils.Terminal.Colors;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.xml.XMLCore;
import itmo.kxrxh.lab5.utils.xml.XmlReader;

import java.io.FileNotFoundException;
import java.util.Scanner;

import static itmo.kxrxh.lab5.Main.ENVIRONMENT_VARIABLE;


public class CollectionCore {
    private CollectionManager collectionManager;
    private CommandBuilder commandBuilder;

    private XMLCore xmlCore;
    private static final String buildersPath = "itmo.kxrxh.lab5.types.builders";

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
            xmlCore = new XMLCore(dotEnv.get(ENVIRONMENT_VARIABLE), collectionManager);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public CollectionCore fillOutCollection() {
        XmlReader xmlReader;
        try {
            xmlReader = xmlCore.newXMLReader(ProductCollector.class, Product.class.getSimpleName(), buildersPath);
        } catch (FileNotFoundException e) {
            System.out.println(Colors.ANSI_YELLOW + "Creating new collection..." + Colors.ANSI_RESET);
            return this;
        }
        // Parsing collection from file
        System.out.println(Colors.ANSI_YELLOW + "Reading collection from file..." + Colors.ANSI_RESET);
        ProductCollector parsedProductCollector = xmlReader.parse();
        collectionManager.collection().addAll(parsedProductCollector);
        System.out.println(Colors.ANSI_GREEN + "Collection was successfully read from file." + Colors.ANSI_RESET);
        return this;
    }

    public void run() {
        Scanner in = new Scanner(System.in);
        // Program loop
        while (true) {
            // Reading user input
            System.out.print("\u001B[35m>> \u001B[37m");
            String userInput = in.nextLine();
            System.out.print(Colors.ANSI_RESET);
            try {
                commandBuilder.buildCommand(userInput).execute();
            } catch (Exception e) {
                System.out.println(Colors.ANSI_RED + "Error: " + e.getMessage() + Colors.ANSI_RESET);
            }
        }
    }

}

