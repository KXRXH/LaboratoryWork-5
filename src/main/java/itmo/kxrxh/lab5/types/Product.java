package itmo.kxrxh.lab5.types;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

/**
 * The type Product.
 *
 * @author kxrxh
 * @see Coordinates
 * @see UnitOfMeasure
 * @see Organization
 */
public class Product implements Comparable<Product> {
    private Integer id; // Значение поля должно быть больше 0, Поле не может быть null, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private final String name; // Поле не может быть null, Строка не может быть пустой

    private final Coordinates coordinates; // Поле не может быть null

    private final java.time.LocalDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private final Double price; // Значение поля должно быть больше 0
    private final String partNumber; // Поле должно быть уникальным, Длина строки должна быть не меньше 18, Поле не может быть null, Строка не может быть пустой
    private final UnitOfMeasure unitOfMeasure; // Поле не может быть null

    private final Organization manufacturer; // Поле не может быть null
    private final float manufactureCost;


    /**
     * Instantiates a new Product. Used for deserialization.
     *
     * @param id              id
     * @param name            name
     * @param coordinates     coordinates
     * @param creationDate    creation date
     * @param price           price
     * @param partNumber      part number
     * @param unitOfMeasure   unit of measure
     * @param manufacturer    manufacturer
     * @param manufactureCost manufacture cost
     */
    public Product(Integer id, @NotNull String name, @NotNull Coordinates coordinates, @NotNull LocalDateTime creationDate, @NotNull Double price, @NotNull String partNumber, @NotNull UnitOfMeasure unitOfMeasure, @NotNull Organization manufacturer, float manufactureCost) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be less than or equal to 0");
        }
        if (partNumber.isEmpty()) {
            throw new IllegalArgumentException("Part number cannot be empty");
        }
        if (partNumber.length() < 18) {
            throw new IllegalArgumentException("Part number length cannot be less than 18");
        }
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.partNumber = partNumber;
        this.unitOfMeasure = unitOfMeasure;
        this.manufacturer = manufacturer;
        this.manufactureCost = manufactureCost;
    }

    /**
     * Get id
     *
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Get name
     *
     * @return name
     */
    public @NotNull String getName() {
        return name;
    }

    /**
     * Get coordinates
     *
     * @return coordinates coordinates
     */
    public @NotNull Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Get creation date
     *
     * @return creation date
     */
    public @NotNull LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Get price
     *
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Get part number
     *
     * @return part number
     */
    public @NotNull String getPartNumber() {
        return partNumber;
    }

    /**
     * Get unit of measure
     *
     * @return unit of measure
     */
    public @NotNull UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Get manufacturer
     *
     * @return manufacturer
     */
    public @NotNull Organization getManufacturer() {
        return manufacturer;
    }

    /**
     * Get manufacture cost
     *
     * @return manufacture cost
     */
    public float getManufactureCost() {
        return manufactureCost;
    }

    /**
     * @param pivot product to compare with
     * @return 0 if equal, 1 if greater, -1 if less
     */
    @Override
    public int compareTo(Product pivot) {
        int sum = 0;
        sum += this.getCoordinates().compareTo(pivot.getCoordinates());
        sum += this.getCreationDate().compareTo(pivot.getCreationDate());
        sum += this.getPrice().compareTo(pivot.getPrice());
        sum += this.getPartNumber().compareTo(pivot.getPartNumber());
        sum += this.getUnitOfMeasure().compareTo(pivot.getUnitOfMeasure());
        sum += this.getManufacturer().compareTo(pivot.getManufacturer());
        if (sum >= 1) {
            return 1;
        }
        if (sum <= -1) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", coordinates=" + coordinates + ", creationDate=" + creationDate + ", price=" + price + ", partNumber='" + partNumber + '\'' + ", unitOfMeasure=" + unitOfMeasure + ", manufacturer=" + manufacturer + ", manufactureCost=" + manufactureCost + '}';
    }
}
