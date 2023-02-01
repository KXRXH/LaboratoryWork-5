package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.Executable;

/**
 * Help command - print all commands\
 *
 * @author kxrxh
 */
public record HelpCommand() implements Executable {
    @Override
    public void execute() {
        System.out.println("help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "save : сохранить коллекцию в файл\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу (без сохранения в файл)\n" +
                "head : вывести первый элемент коллекции\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n" +
                "sum_of_price : вывести сумму значений поля price для всех элементов коллекции\n" +
                "average_of_manufacture_cost : вывести среднее значение поля manufactureCost для всех элементов коллекции\n" +
                "count_greater_than_manufacturer manufacturer : вывести количество элементов, значение поля manufacturer которых больше заданного");
    }
}
