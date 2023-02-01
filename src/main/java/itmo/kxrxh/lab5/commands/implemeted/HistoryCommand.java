package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.types.SizedStack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * History command
 *
 * @author kxrxh
 */
public final class HistoryCommand implements Executable {
    /**
     * Contains last 7 commands
     */
    private final SizedStack<String> history;

    /**
     * Instantiates a new History command.
     *
     * @param history history stack
     */
    public HistoryCommand(SizedStack<String> history) {
        this.history = history;
    }

    @Override
    public void execute() {
        AtomicInteger c = new AtomicInteger();
        history.forEach(command -> System.out.println(c.incrementAndGet() + ". " + command));
    }
}
