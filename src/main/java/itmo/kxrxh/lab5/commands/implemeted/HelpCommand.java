package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.Command;
import itmo.kxrxh.lab5.commands.Executable;

/**
 * Help command - print all commands\
 *
 * @author kxrxh
 */
@Command(name = "Help")
public record HelpCommand() implements Executable {
    @Override
    public void execute() {
        System.out.println("\u001B[36mhelp\u001B[0m : \u001B[34mвывести справку по доступным командам\u001B[0m\n" + "\u001B[36minfo\u001B[0m : \u001B[34mвывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\u001B[0m\n" + "\u001B[36mshow\u001B[0m : \u001B[34mвывести в стандартный поток вывода все элементы коллекции в строковом представлении\u001B[0m\n" + "\u001B[36madd \u001B[33m{element}\u001B[0m : \u001B[34mдобавить новый элемент в коллекцию\u001B[0m\n" + "\u001B[36mupdate id \u001B[33m{element}\u001B[0m : \u001B[34mобновить значение элемента коллекции, id которого равен заданному\u001B[0m\n" + "\u001B[36mremove_by_id id\u001B[0m : \u001B[34mудалить элемент из коллекции по его id\u001B[0m\n" + "\u001B[36mclear\u001B[0m : \u001B[34mочистить коллекцию\u001B[0m\n" + "\u001B[36msave\u001B[0m : \u001B[34mсохранить коллекцию в файл\u001B[0m\n" + "\u001B[36mexecute_script file_name\u001B[0m : \u001B[34mсчитать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\u001B[0m\n" + "\u001B[36mexit\u001B[0m : \u001B[34mзавершить программу (без сохранения в файл)\u001B[0m\n" + "\u001B[36mhead\u001B[0m : \u001B[34mвывести первый элемент коллекции\u001B[0m\n" + "\u001B[36madd_if_max \u001B[33m{element}\u001B[0m : \u001B[34mдобавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\u001B[0m\n" + "\u001B[36mremove_greater \u001B[33m{element}\u001B[0m : \u001B[34mудалить из коллекции все элементы, превышающие заданный\u001B[0m\n" + "\u001B[36msum_of_price\u001B[0m : \u001B[34mвывести сумму значений поля price для всех элементов коллекции\u001B[0m\n" + "\u001B[36maverage_of_manufacture_cost\u001B[0m : \u001B[34mвывести среднее значение поля manufactureCost для всех элементов коллекции\u001B[0m");
    }
}
