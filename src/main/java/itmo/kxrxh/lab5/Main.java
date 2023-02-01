package itmo.kxrxh.lab5;


import itmo.kxrxh.lab5.collection.ModLinkedList;
import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CommandReader;

import java.util.Scanner;


/**
 * Main class
 *
 * @author kxrxh
 */
public final class Main {
    // V: 369491
    private static final Scanner in = new Scanner(System.in);
    private static final ModLinkedList modLinkedList = new ModLinkedList();

    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager(modLinkedList);
        CommandReader commandReader = new CommandReader(collectionManager);
        // Program loop
        while (true) {
            // Read user input
            System.out.print(">> ");
            String userInput = in.nextLine();
            commandReader.readLine(userInput).execute();
        }
    }

}
