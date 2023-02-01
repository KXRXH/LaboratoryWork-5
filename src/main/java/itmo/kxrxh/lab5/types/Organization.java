package itmo.kxrxh.lab5.types;

import itmo.kxrxh.lab5.utils.IdGenerator;
import org.jetbrains.annotations.NotNull;


public class Organization {
    /**
     * Unique id
     */
    private final long id;
    /**
     * Name of the organization
     */
    @NotNull
    private String name;
    /**
     * Number of employees
     */
    private int employeesCount;
    /**
     * Type of the organization
     */
    @NotNull
    private OrganizationType type;
    /**
     * Postal address of the organization
     */
    @NotNull
    private Address postalAddress;

    /**
     * @param name           name of the organization
     * @param employeesCount number of employees
     * @param type           type of the organization
     * @param postalAddress  postal address of the organization
     */
    public Organization(@NotNull String name, int employeesCount, @NotNull OrganizationType type, @NotNull Address postalAddress) {
        // employeesCount > 0
        if (employeesCount <= 0) {
            throw new RuntimeException("Employees count must be greater than 0");
        }
        // name is not empty
        if (name.isEmpty()) {
            throw new RuntimeException("Name cannot be empty");
        }
        // Generating unique id
        this.id = IdGenerator.generateLongId();
        this.name = name;
        this.employeesCount = employeesCount;
        this.type = type;
        this.postalAddress = postalAddress;
    }

    public @NotNull String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getEmployeesCount() {
        return employeesCount;
    }

    public @NotNull OrganizationType getType() {
        return type;
    }

    public @NotNull Address getPostalAddress() {
        return postalAddress;
    }
}
