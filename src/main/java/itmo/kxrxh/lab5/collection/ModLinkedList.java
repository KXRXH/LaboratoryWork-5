package itmo.kxrxh.lab5.collection;


import itmo.kxrxh.lab5.types.Product;

import java.util.LinkedList;

/**
 * ModLinkedList class
 *
 * @author kxrxh
 * @see LinkedList
 * @see Product
 * @see BasicCollection
 * @see SortableCollection
 */
public class ModLinkedList extends LinkedList<Product> implements BasicCollection, SortableCollection {
    /**
     * Instantiates a new Mod linked list.
     */
    public ModLinkedList() {
        super();
    }

    /**
     * Print info about collection
     */
    public void getInfo() {
        // print class name
        System.out.println("Class: " + this.getClass().getSimpleName());
        // print type of elements
        System.out.println("Type: " + this.getClass().arrayType().descriptorString());
        // print size
        System.out.println("Size: " + this.size());
        // print elements
        System.out.println("Elements: " + this);
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
        this.clear();
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
        if (left < right) {
            int pi = partition(left, right);
            quickSort(left, pi - 1);
            quickSort(pi + 1, right);
        }
    }

    /**
     * Partition. Used in quickSort.
     *
     * @param low  begin of the LinkedList (index)
     * @param high end of the LinkedList (index)
     * @return index of the pivot
     */
    private int partition(int low, int high) {
        Product pivot = this.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (this.get(j).compareTo(pivot) < 0) {
                i++;
                Product temp = this.get(i);
                this.set(i, this.get(j));
                this.set(j, temp);
            }
        }
        Product temp = this.get(i + 1);
        this.set(i + 1, this.get(high));
        this.set(high, temp);
        return i + 1;
    }
}
