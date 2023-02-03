package itmo.kxrxh.lab5.types.builders;

import itmo.kxrxh.lab5.types.Address;

/**
 * Builder for Address
 * <p>
 * Used for creating Address objects, while parsing XML.
 *
 * @author kxrxh
 * @see Address
 * @see Builder
 */
public final class AddressBuilder implements Builder {
    private String street;
    private String zipcode;

    @Override
    public Address build() {
        return new Address(street, zipcode);
    }
}
