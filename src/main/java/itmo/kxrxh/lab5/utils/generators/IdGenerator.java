package itmo.kxrxh.lab5.utils.generators;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * IdGenerator class. Used to generate unique ids.
 *
 * @author kxrxh
 */
public final class IdGenerator {
    /**
     * Unique id. Starts from 1
     */
    private static final AtomicLong long_id_counter = new AtomicLong(0);
    /**
     * Unique id. Starts from 1
     */
    private static final AtomicInteger int_id_counter = new AtomicInteger(0);

    /**
     * Generate unique id of type long
     *
     * @return unique id
     */
    public static Long generateLongId() {
        return long_id_counter.incrementAndGet();
    }

    /**
     * Generate unique id of type int
     *
     * @return unique id
     */
    public static Integer generateIntId() {
        return int_id_counter.incrementAndGet();
    }
}
