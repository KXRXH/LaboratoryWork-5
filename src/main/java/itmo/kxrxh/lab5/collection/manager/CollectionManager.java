package itmo.kxrxh.lab5.collection.manager;

import itmo.kxrxh.lab5.collection.ProductCollector;
import itmo.kxrxh.lab5.types.Product;
import itmo.kxrxh.lab5.utils.terminal.Printer;
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
     * @param id id of product
     * @return true if product with given id is in collection, else false
     */
    public boolean collectionContainsItemWithId(long id) {
        for (Product product : collection) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints collection
     */
    public void show() {
        int c = 1;
        for (Product product : collection) {
            System.out.println(c++ + ". Product:");
            Printer.print(product, 1);
        }
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

    public void updateItem(Product newProduct) {
        this.removeById(newProduct.getId());
        collection.add(newProduct);
    }


    /**
     * Removes all products which greater than given
     *
     * @param pivotProduct product to compare with
     */
    public void removeGreaterItems(Product pivotProduct) {
        collection.removeIf(product -> product.compareTo(pivotProduct) > 0);
    }

    public void addIfMax(Product newProduct) {
        if (collection.size() == 0) {
            collection.add(newProduct);
            return;
        }
        Product maxProduct = collection.getMax();
        if (maxProduct.compareTo(newProduct) > 0) {
            collection.add(newProduct);
        }
    }
}
