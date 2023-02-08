package itmo.kxrxh.lab5.types.builders;

import itmo.kxrxh.lab5.types.Address;
import itmo.kxrxh.lab5.types.Organization;
import itmo.kxrxh.lab5.types.OrganizationType;
import itmo.kxrxh.lab5.utils.annotations.Generated;
import itmo.kxrxh.lab5.utils.annotations.NonNull;
import itmo.kxrxh.lab5.utils.annotations.Unique;
import itmo.kxrxh.lab5.utils.annotations.Value;

/**
 * Builder for Organization. Used for creating Organization objects, while parsing XML.
 *
 * @author kxrxh
 * @see Organization
 * @see Builder
 */
public final class OrganizationBuilder implements Builder {
    @Value(min = 0)
    @Generated
    @Unique
    private long id;

    @NonNull
    private String name;

    @Value(min = 0)
    private int employeesCount;

    @NonNull
    private OrganizationType type;

    @NonNull
    private Address address;

    @Override
    public Object build() {
        return new Organization(id, name, employeesCount, type, address);
    }
}
