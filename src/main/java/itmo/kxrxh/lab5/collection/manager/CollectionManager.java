package itmo.kxrxh.lab5.collection.manager;

import itmo.kxrxh.lab5.collection.ModLinkedList;
import itmo.kxrxh.lab5.types.Product;
import org.jetbrains.annotations.TestOnly;

/**
 * Record for managing collection
 *
 * @author kxrxh
 */
public record CollectionManager(ModLinkedList collection) {
    public void clear() {
        collection.clear();
    }

    public void getInfo() {
        collection.getInfo();
    }

    public void show() {
        collection.forEach(System.out::println);
    }

    public Product head() {
        if (collection.isEmpty()) {
            return null;
        }
        return collection.get(0);
    }

    @TestOnly
    public double sumOfPrice() {
        // TODO: untested
        return collection.stream().mapToDouble(Product::getPrice).sum();
    }
}
