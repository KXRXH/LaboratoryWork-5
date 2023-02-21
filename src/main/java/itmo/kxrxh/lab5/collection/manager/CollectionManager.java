package itmo.kxrxh.lab5.collection.manager;

import itmo.kxrxh.lab5.collection.ProductCollector;
import itmo.kxrxh.lab5.types.Product;
import org.jetbrains.annotations.Nullable;

/**
 * Record for managing collection
 *
 * @author kxrxh
 */
public record CollectionManager(ProductCollector collection) {
    /**
     * Clear collection
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Prints info about collection
     */
    public void getInfo() {
        collection.getInfo();
    }

    /**
     * Prints collection
     */
    public void show() {
        collection.forEach(System.out::println);
    }

    /**
     * Returns first element of the collection
     *
     * @return first element of the collection
     */
    public @Nullable Product head() {
        if (collection.isEmpty()) {
            return null;
        }
        return collection.get(0);
    }

    /**
     * Returns sum of price of all products
     *
     * @return sum of price of all products
     */
    public double sumOfPrice() {
        return collection.stream().mapToDouble(Product::getPrice).sum();
    }

    /**
     * Removes product by id
     *
     * @param id id of the product
     */
    public void removeById(long id) {
        collection.removeById(id);
    }

    public void add(Product product) {
        collection.add(product);
    }
}
