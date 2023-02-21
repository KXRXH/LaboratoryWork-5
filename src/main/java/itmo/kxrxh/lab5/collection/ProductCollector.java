package itmo.kxrxh.lab5.collection;


import itmo.kxrxh.lab5.types.Product;

import java.util.LinkedList;

/**
 * ProductCollector class
 *
 * @author kxrxh
 * @see LinkedList
 * @see Product
 * @see BasicCollection
 * @see SortableCollection
 */
public class ProductCollector extends LinkedList<Product> implements BasicCollection, SortableCollection {
    /**
     * Instantiates a new Product collector.
     */
    public ProductCollector() {
        super();
    }

    /**
     * Print info about collection
     */
    public void getInfo() {
        System.out.println("Name of the collection: " + this.getClass().getName());
        if (this.isEmpty()) {
            System.out.println("The linked list is empty.");
            return;
        }
        System.out.println("Type of collection's items: " + this.getFirst().getClass().getName());
        System.out.println("Size: " + this.size());
        System.out.println("List of elements:");
        System.out.println("[");
        this.forEach(System.out::println);
        System.out.println("]");
    }

    /**
     * Clear collection
     */
    @Override
    public void clear() {
        if (this.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }
        super.clear();
    }

    /**
     * Remove element by id
     *
     * @param id of the product
     */
    public void removeById(long id) {
        super.removeIf(product -> product.getId() == id);
    }

    /**
     * Sort collection
     */
    @Override
    public void sort() {
        this.sort(Product::compareTo);
    }

    /**
     * Quick sort.
     *
     * @param left  begin of the LinkedList (index)
     * @param right end of the LinkedList (index)
     */
    @Override
    public void quickSort(int left, int right) {
        if (left >= right) {
            return;
        }

        int pivotIndex = partition(left, right);
        quickSort(left, pivotIndex - 1);
        quickSort(pivotIndex + 1, right);
    }

    private int partition(int low, int high) {
        Product pivot = get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (get(j).compareTo(pivot) < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }


    private void swap(int i, int j) {
        Product temp = get(i);
        set(i, get(j));
        set(j, temp);
    }
}
