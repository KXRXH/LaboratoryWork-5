package itmo.kxrxh.lab5.types;

import org.jetbrains.annotations.NotNull;

/**
 * The type Address.
 *
 * @author kxrxh
 */
public class Address implements Comparable<Address> {
    private final String street; // Строка не может быть пустой, Поле не может быть null

    private final String zipCode; // Поле может быть null

    /**
     * Instantiates a new Address.
     *
     * @param street  the street
     * @param zipCode the zip code
     */
    public Address(@NotNull String street, String zipCode) {
        if (street.isEmpty()) {
            throw new IllegalArgumentException("Street cannot be empty");
        }
        this.street = street;
        this.zipCode = zipCode;
    }

    /**
     * Getter for street
     *
     * @return street
     */
    public @NotNull String getStreet() {
        return street;
    }

    /**
     * Getter for zipCode
     *
     * @return zipCode zip code
     */
    public String getZipCode() {
        return zipCode;
    }


    /**
     * @return string representation of object
     */
    @Override
    public String toString() {
        return "Address{" + "street='" + street + '\'' + ", zipCode='" + zipCode + '\'' + '}';
    }

    /**
     * @param o object to compare
     * @return true if objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!street.equals(address.street)) return false;
        return zipCode.equals(address.zipCode);
    }

    @Override
    public int compareTo(Address address) {
        int sum = 0;
        sum += this.street.compareTo(address.street);
        sum += this.zipCode.compareTo(address.zipCode);
        if (sum <= -1) {
            return -1;
        }
        if (sum >= 1) {
            return 1;
        }
        return 0;
    }
}
