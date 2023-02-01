package itmo.kxrxh.lab5.commands;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.implemeted.*;
import itmo.kxrxh.lab5.utils.types.SizedStack;

import java.io.File;
import java.io.FileReader;

public class CommandReader {

    private final CollectionManager collectionManager;
    private final SizedStack<String> history = new SizedStack<>(7);

    public CommandReader(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public Executable readLine(String line) {
        String[] args = line.split(" ");
        String commandName = args[0];
        String[] commandArgs = new String[args.length - 1];
        System.arraycopy(args, 1, commandArgs, 0, args.length - 1);
        history.push(commandName);
        switch (commandName) {
            case "help":
                return new HelpCommand();
            case "info":
                return new InfoCommand(collectionManager);
            case "show":
                return new ShowCommand(collectionManager);
            case "clear":
                return new ClearCommand(collectionManager);
            case "head":
                return new HeadCommand(collectionManager);
            case "sum_of_price":
                return new SumOfPriceCommand(collectionManager);
            case "history":
                return new HistoryCommand(history);
            case "exit":
                return new ExitCommand();
            default:
                return new UnknownCommand();
        }
    }

    public void readFile(FileReader fileReader) {

    }
}
