package itmo.kxrxh.lab5.types.builders;

/**
 * Interface for builders
 * <p>
 * Used for creating objects, while parsing XML.
 * <p>
 * !Guide: use the same names for fields and tags in XML file.
 * </p>
 *
 * @author kxrxh
 * @see itmo.kxrxh.lab5.types.builders.ProductBuilder
 * @see itmo.kxrxh.lab5.types.builders.CoordinatesBuilder
 * @see itmo.kxrxh.lab5.types.builders.OrganizationBuilder
 */
public interface Builder {
    Object build();
}
