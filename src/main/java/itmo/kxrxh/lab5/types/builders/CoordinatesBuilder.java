package itmo.kxrxh.lab5.types.builders;

import itmo.kxrxh.lab5.types.Coordinates;
import itmo.kxrxh.lab5.utils.annotations.NonNull;
import itmo.kxrxh.lab5.utils.annotations.Value;

/**
 * Builder for Coordinates
 * <p>
 * Used for creating Coordinates objects, while parsing XML.
 *
 * @author kxrxh
 * @see Coordinates
 * @see Builder
 */
public final class CoordinatesBuilder implements Builder {
    @NonNull
    @Value(max = 444)
    private Integer x;
    @Value(min = -368)
    private double y;

    @Override
    public Coordinates build() {
        return new Coordinates(x, y);
    }
}
