package itmo.kxrxh.lab5.types;

import itmo.kxrxh.lab5.utils.IdGenerator;
import itmo.kxrxh.lab5.utils.Time;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class Product {
    /**
     * ID of the product. Is unique, cannot be null, is generated automatically and greater than 0.
     */
    private final Integer id; // Значение поля должно быть больше 0, Поле не может быть null, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    /**
     * Name of the product. Cannot be null or empty.
     */
    @NotNull
    private String name; // Поле не может быть null, Строка не может быть пустой
    /**
     * Coordinates of the product. Cannot be null.
     */
    @NotNull
    private Coordinates coordinates; // Поле не может быть null
    /**
     * Creation date of the product. Cannot be null, is generated automatically.
     */
    @NotNull
    private java.time.LocalDateTime creationDate; // Поле не может быть null, Значение этого поля должно генерироваться автоматически
    /**
     * Price of the product. Cannot be null, is greater than 0.
     */
    private Double price; // Значение поля должно быть больше 0
    @NotNull
    private String partNumber; // Поле должно быть уникальным, Длина строки должна быть не меньше 18, Поле не может быть null, Строка не может быть пустой
    /**
     * Unit of measure of the product. Cannot be null.
     */
    @NotNull
    private UnitOfMeasure unitOfMeasure; // Поле не может быть null

    /**
     * Manufacturer of the product. Cannot be null.
     */
    @NotNull
    private Organization manufacturer; // Поле не может быть null
    /**
     * Manufacture cost of the product
     */
    private float manufactureCost;

    /**
     * @param name            name of the product
     * @param coordinates     coordinates of the product
     * @param price           price of the product
     * @param partNumber      part number of the product
     * @param unitOfMeasure   unit of measure of the product
     * @param manufacturer    manufacturer of the product
     * @param manufactureCost manufacture cost of the product
     */
    public Product(@NotNull String name, @NotNull Coordinates coordinates, @NotNull Double price, @NotNull String partNumber, @NotNull UnitOfMeasure unitOfMeasure, @NotNull Organization manufacturer, float manufactureCost) {
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
        this.id = IdGenerator.generateIntId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = Time.getTime();
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
     * @return coordinates
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
}
