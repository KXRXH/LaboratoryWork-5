package itmo.kxrxh.lab5.types.builders;

import itmo.kxrxh.lab5.types.Address;
import itmo.kxrxh.lab5.types.Organization;
import itmo.kxrxh.lab5.types.OrganizationType;

/**
 * Builder for Organization. Used for creating Organization objects, while parsing XML.
 *
 * @author kxrxh
 * @see Organization
 * @see Builder
 */
public final class OrganizationBuilder implements Builder {
    private long id;
    private String name;
    private int employeesCount;

    private OrganizationType type;

    private Address address;

    @Override
    public Object build() {
        return new Organization(id, name, employeesCount, type, address);
    }
}
