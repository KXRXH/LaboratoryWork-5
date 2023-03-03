package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.commands.Command;
import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.types.SizedStack;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * History command
 *
 * @author kxrxh
 */
@Command(name = "History")
public final class HistoryCommand implements Executable {
    /**
     * Contains last 7 commands
     *
     * @see SizedStack
     */
    private final SizedStack<Executable> history;

    /**
     * Instantiates a new History command.
     *
     * @param history history stack
     */
    public HistoryCommand(SizedStack<Executable> history) {
        this.history = history;
    }

    @Override
    public void execute() {
        AtomicInteger index = new AtomicInteger();
        history.forEach(command -> System.out.println(index.incrementAndGet() + ". " + command.getClass().getAnnotation(Command.class).name()));
    }
}
