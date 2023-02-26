package itmo.kxrxh.lab5.types;

import org.jetbrains.annotations.NotNull;


/**
 * The type Organization.
 *
 * @author kxrxh
 * @see OrganizationType
 * @see Address
 */
public class Organization {
    private final long id;

    private final String name;

    private final int employeesCount;

    private final OrganizationType type;

    private final Address postalAddress;

    /**
     * @param id             unique id
     * @param name           name of the organization
     * @param employeesCount number of employees
     * @param type           type of the organization
     * @param postalAddress  postal address of the organization
     */
    public Organization(long id, @NotNull String name, int employeesCount, @NotNull OrganizationType type, @NotNull Address postalAddress) {
        // employeesCount > 0
        if (employeesCount <= 0) {
            throw new RuntimeException("Employees count must be greater than 0");
        }
        // name is not empty
        if (name.isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }
        // Generating unique id
        this.id = id;
        this.name = name;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public @NotNull String getName() {
        return name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets employees count.
     *
     * @return the employees count
     */
    public int getEmployeesCount() {
        return employeesCount;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public @NotNull OrganizationType getType() {
        return type;
    }

    /**
     * Gets postal address.
     *
     * @return the postal address
     */
    public @NotNull Address getPostalAddress() {
        return postalAddress;
    }

    @Override
    public String toString() {
        return "Organization{" + "id=" + id + ", name='" + name + '\'' + ", employeesCount=" + employeesCount + ", type=" + type + ", postalAddress=" + postalAddress + '}';
    }

    public int compareTo(Organization organization) {
        int sum = 0;
        sum += this.getName().compareTo(organization.getName());
        sum += this.getType().compareTo(organization.getType());
        sum += this.getEmployeesCount() - organization.getEmployeesCount();
        sum += this.getPostalAddress().compareTo(organization.getPostalAddress());
        if (sum <= -1) {
            return -1;
        }
        if (sum >= 1) {
            return 1;
        }
        return 0;
    }
}
