package itmo.kxrxh.lab5.types.builders;

import itmo.kxrxh.lab5.types.Coordinates;
import itmo.kxrxh.lab5.types.Organization;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.types.UnitOfMeasure;

import java.time.LocalDateTime;

/**
 * Builder for Product. Used for creating Product objects, while parsing XML.
 *
 * @author kxrxh
 * @see Product
 * @see Builder
 */
public final class ProductBuilder implements Builder {
    private Integer id;
    private String name;
    private Organization organization;
    private Coordinates coordinates;
    private Double price;
    private LocalDateTime creationDate;
    private String partNumber;
    private UnitOfMeasure unitOfMeasure;
    private float manufactureCost;

    @Override
    public Product build() {
        return new Product(id, name, coordinates, creationDate, price, partNumber, unitOfMeasure, organization, manufactureCost);
    }
}
