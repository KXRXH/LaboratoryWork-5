package itmo.kxrxh.lab5.collection;


import itmo.kxrxh.lab5.types.Product;

import java.util.LinkedList;

/**
 * ModLinkedList class
 *
 * @author kxrxh
 */
public class ModLinkedList extends LinkedList<Product> implements Collection, SortableCollection {
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
     * Sort collection
     */
    @Override
    public void sort() {
        this.sort(null);
    }
}
