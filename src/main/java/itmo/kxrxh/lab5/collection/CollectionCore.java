package itmo.kxrxh.lab5.collection;

import itmo.kxrxh.lab5.Constants;
import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CommandBuilder;
import itmo.kxrxh.lab5.commands.CommandInvoker;
import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.TempFiles;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.terminal.Colors;
import itmo.kxrxh.lab5.utils.xml.XML;
import itmo.kxrxh.lab5.utils.xml.XMLReader;

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

    private XML xmlCore;

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
        try {
            xmlCore = new XML(new File(dotEnv.get(Constants.ENVIRONMENT_VARIABLE)));
        } catch (NoSuchFieldException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return this;
    }

    public boolean restoreCollection() {
        if (TempFiles.getTempFiles().length == 0) {
            return false;
        }
        System.out.println(Colors.ANSI_YELLOW + "The previous session was terminated without saving." + " You can restore a collection from a restore point." + "\nTo select restore point, just write it number." + "\nTo skip restoration just write anything." + "\nAvailable restore points:" + Colors.ANSI_RESET);
        ArrayList<ProductCollector> restorePoints = new ArrayList<>();
        int c = 1;
        for (File tmpFile : TempFiles.getTempFiles()) {
            ProductCollector pc = xmlCore.newReader().parse(tmpFile);
            restorePoints.add(pc);
            System.out.println(Colors.ANSI_CYAN + c++ + ". " + pc.toString() + Colors.ANSI_RESET);
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
            System.out.print(Colors.ANSI_RESET);
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
        System.out.println("\u001B[32mCollection was successfully read from file.\u001B[0m");
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
                Executable executable = commandBuilder.buildCommand(userInput);
                commandInvoker.execute(executable);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}

