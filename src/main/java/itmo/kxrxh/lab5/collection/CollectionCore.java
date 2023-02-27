package itmo.kxrxh.lab5.collection;

import itmo.kxrxh.lab5.Constants;
import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.commands.CommandInvoker;
import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.TempFiles;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.terminal.Colors;
import itmo.kxrxh.lab5.utils.xml.Xml;
import itmo.kxrxh.lab5.utils.xml.XmlReader;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class CollectionCore {
    private static final CommandBuilder commandBuilder = new CommandBuilder();
    private static final CommandInvoker commandInvoker = new CommandInvoker();
    private static final DotEnv dotEnv;
    private static CollectionManager collectionManager;

    static {
        dotEnv = new DotEnv(".env");
        // Reading .env file
        dotEnv.load();
    }

    private Xml xmlCore;

    public CollectionCore() {
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public static CommandBuilder getCommandBuilder() {
        return commandBuilder;
    }

    public static CommandInvoker getInvoker() {
        return commandInvoker;
    }

    public CollectionCore init() {
        ProductCollector productCollector = new ProductCollector();
        collectionManager = new CollectionManager(productCollector);
        xmlCore = new Xml(new File(dotEnv.get(Constants.EnvironmentVariable)));
        return this;
    }

    public boolean restoreCollection() {
        if (TempFiles.getTempFiles().length == 0) {
            return false;
        }
        System.out.println(Colors.AsciiYellow + "The previous session was terminated without saving." + " You can restore a collection from a restore point." + "\nTo select restore point, just write it number." + "\nTo skip restoration just write anything." + "\nAvailable restore points:" + Colors.AsciiReset);
        ArrayList<ProductCollector> restorePoints = new ArrayList<>();
        int c = 1;
        for (File tmpFile : TempFiles.getTempFiles()) {
            ProductCollector pc = xmlCore.newReader().parse(tmpFile);
            restorePoints.add(pc);
            System.out.println(Colors.AsciiCyan + c++ + ". " + pc.toString() + Colors.AsciiReset);
        }
        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.print("\u001B[35m>> \u001B[37m");
            // Reading user input
            String userInput;
            try {
                userInput = in.nextLine();
            } catch (NoSuchElementException e) {
                System.err.println("EOF");
                return false;
            }
            System.out.print(Colors.AsciiReset);
            int restorePoint;
            try {
                restorePoint = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                return false;
            }
            if (restorePoint < 1 || restorePoint > restorePoints.size()) {
                System.err.println("Invalid restore point");
                continue;
            }
            collectionManager.collection().addAll(restorePoints.get(restorePoint - 1));
            return true;
        }
    }

    public void fillOutCollection() {
        XmlReader xmlReader;
        xmlReader = xmlCore.newReader();
        // Parsing collection from file
        System.out.println(Colors.AsciiYellow + "Reading collection from file..." + Colors.AsciiReset);
        ProductCollector parsedProductCollector = new ProductCollector();
        try {
            parsedProductCollector = xmlReader.parse();
            System.out.println("\u001B[32mCollection was successfully read from file.\u001B[0m");
        } catch (Exception e) {
            System.out.println(Colors.AsciiPurple + "Unable to find collection file.");
            System.out.println(Colors.AsciiGreen + "Creating new collection" + Colors.AsciiReset);
        }
        collectionManager.collection().addAll(parsedProductCollector);
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
            System.out.print(Colors.AsciiReset);
            try {
                Executable executable = commandBuilder.buildCommand(userInput);
                commandInvoker.execute(executable);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

