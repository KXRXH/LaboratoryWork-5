package itmo.kxrxh.lab5.types;

import org.jetbrains.annotations.NotNull;

/**
 * The type Coordinates.
 */
public class Coordinates {
    @NotNull
    private final Integer x; // Максимальное значение поля: 444, Поле не может быть null
    private final double y; // Значение поля должно быть больше -368

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(Integer x, double y) {
        if (x == null) {
            throw new IllegalArgumentException("X cannot be null");
        }
        // max value of x is 444
        if (x > 444) {
            throw new IllegalArgumentException("X cannot be greater than 444");
        }
        // min value of x is -368
        if (x <= -368) {
            throw new IllegalArgumentException("X cannot be less than -368");
        }
        this.x = x;
        this.y = y;
    }
}
