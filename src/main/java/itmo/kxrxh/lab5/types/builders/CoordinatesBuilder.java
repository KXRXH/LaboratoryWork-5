package itmo.kxrxh.lab5.types.builders;

import itmo.kxrxh.lab5.types.Coordinates;

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
    private Integer x;
    private double y;

    @Override
    public Coordinates build() {
        return new Coordinates(x, y);
    }
}
