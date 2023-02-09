package itmo.kxrxh.lab5.types.builders;

import itmo.kxrxh.lab5.types.Coordinates;
import itmo.kxrxh.lab5.types.Organization;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.types.UnitOfMeasure;
import itmo.kxrxh.lab5.utils.annotations.*;

import java.time.LocalDateTime;

/**
 * Builder for Product. Used for creating Product objects, while parsing XML.
 *
 * @author kxrxh
 * @see Product
 * @see Builder
 */
public final class ProductBuilder implements Builder {
    @Value(min = 0)
    @Generated
    @Unique
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private Organization organization;

    @NonNull
    private Coordinates coordinates;

    @Value(min = 0)
    private Double price;

    @Generated
    @NonNull
    private LocalDateTime creationDate;

    @Unique
    @Length(min = 18)
    private String partNumber;
    @NonNull
    private UnitOfMeasure unitOfMeasure;
    @NonNull
    private float manufactureCost;

    @Override
    public Product build() {
        return new Product(id, name, coordinates, creationDate, price, partNumber, unitOfMeasure, organization, manufactureCost);
    }
}
