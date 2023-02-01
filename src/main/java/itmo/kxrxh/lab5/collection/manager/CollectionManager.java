package itmo.kxrxh.lab5.collection.manager;

import itmo.kxrxh.lab5.collection.ModLinkedList;
import itmo.kxrxh.lab5.types.Product;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

/**
 * Record for managing collection
 *
 * @author kxrxh
 */
public record CollectionManager(ModLinkedList collection) {
    /**
     * Clear collection
     */
    public void clear() {
        collection.clear();
    }

    /**
     * Print info about collection
     */
    public void getInfo() {
        collection.getInfo();
    }

    /**
     * Print collection
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
     * Return sum of price of all products
     *
     * @return sum of price of all products
     */
    @TestOnly
    public double sumOfPrice() {
        // TODO: untested
        return collection.stream().mapToDouble(Product::getPrice).sum();
    }

    /**
     * Remove product by id
     *
     * @param id id of the product
     */
    public void removeById(long id) {
        collection.removeById(id);
    }
}
