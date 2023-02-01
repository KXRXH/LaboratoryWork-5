package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.types.SizedStack;

import java.util.concurrent.atomic.AtomicInteger;

public class HistoryCommand implements Executable {
    private final SizedStack<String> history;

    public HistoryCommand(SizedStack<String> history) {
        this.history = history;
    }

    @Override
    public void execute() {
        AtomicInteger c = new AtomicInteger();
        history.forEach(command -> System.out.println(c.incrementAndGet() + ". " + command));
    }
}
