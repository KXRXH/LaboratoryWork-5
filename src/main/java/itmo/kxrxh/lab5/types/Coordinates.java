package itmo.kxrxh.lab5.types;

/**
 * The type Coordinates.
 *
 * @author kxrxh
 */
public class Coordinates implements Comparable<Coordinates> {
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
        if (x < -368) {
            throw new IllegalArgumentException("X cannot be less than -368");
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }

    /**
     * @param pivot product to compare with
     * @return 0 if equal, 1 if greater, -1 if less
     */
    @Override
    public int compareTo(Coordinates pivot) {
        int sum = 0;
        sum += this.x.compareTo(pivot.x);
        sum += Double.compare(this.y, pivot.y);
        if (sum <= -1) {
            return -1;
        }
        if (sum >= 1) {
            return 1;
        }
        return 0;
    }
}
