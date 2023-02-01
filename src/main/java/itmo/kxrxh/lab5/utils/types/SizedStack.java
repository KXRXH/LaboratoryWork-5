package itmo.kxrxh.lab5.utils.types;

import java.util.Stack;

public class SizedStack<T> extends Stack<T> {
    private final long maxSize;

    public SizedStack(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public T push(T item) {
        while (this.size() >= maxSize) {
            this.remove(0);
        }
        return super.push(item);
    }
}
