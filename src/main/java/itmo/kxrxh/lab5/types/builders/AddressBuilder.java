package itmo.kxrxh.lab5.types.builders;

import itmo.kxrxh.lab5.types.Address;
import itmo.kxrxh.lab5.utils.annotations.NonNull;

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
    @NonNull
    private String street;
    private String zipCode;

    @Override
    public Address build() {
        return new Address(street, zipCode);
    }
}
