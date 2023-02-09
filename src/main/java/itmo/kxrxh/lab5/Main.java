package itmo.kxrxh.lab5;


import itmo.kxrxh.lab5.collection.ProductCollector;
import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.xml.XMLCore;

import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 * Main class
 *
 * @author kxrxh
 * @version 369491
 */
public final class Main {
    // Initializing collection
    private static final ProductCollector modLinkedList = new ProductCollector();

    // Initializing .env file (for reading environment variables (Windows))
    // For reading environment variables (Linux) use EnvReader.get(KEY)
    private static final DotEnv dotEnv;
    private static final String buildersPath = "itmo.kxrxh.lab5.types.builders";

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
        CommandBuilder commandBuilder = new CommandBuilder(collectionManager);
        XMLCore xmlCore = new XMLCore(dotEnv.get("FILE_NAME"), collectionManager);
        try {
            ProductCollector xmlc = xmlCore.newXMLReader(ProductCollector.class, Product.class.getSimpleName(), buildersPath).parse();
            xmlCore.newXMLWriter().writeCollection(xmlc);
            xmlc.quickSort(0, xmlc.size() - 1);
            xmlc.getInfo();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        // Program loop
        while (true) {
            // Reading user input
            System.out.print(">> ");
            String userInput = in.nextLine();
            try {
                commandBuilder.buildCommand(userInput).execute();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
