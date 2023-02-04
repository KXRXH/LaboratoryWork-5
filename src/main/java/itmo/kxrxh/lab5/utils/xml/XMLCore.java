package itmo.kxrxh.lab5.utils.xml;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;

/**
 * Class for creating xml reader and writer
 *
 * @author kxrxh
 * @see XmlReader
 * @see XmlWriter
 * @see XMLHandler
 * @see CollectionManager
 */
public final class XMLCore {
    final String fileName;
    final CollectionManager collectionManager;
    final String indent;

    /**
     * Instantiates a new Xml core.
     *
     * @param fileName          file name
     * @param collectionManager collection manager
     * @param indent            indent (default is " ")
     */
    public XMLCore(String fileName, CollectionManager collectionManager, String indent) {
        this.fileName = fileName;
        this.collectionManager = collectionManager;
        this.indent = indent;
    }

    /**
     * Instantiates a new Xml core.
     *
     * @param fileName          file name
     * @param collectionManager collection manager
     */
    public XMLCore(String fileName, CollectionManager collectionManager) {
        this.fileName = fileName;
        this.collectionManager = collectionManager;
        this.indent = " ";
    }

    /**
     * Instantiates a new Xml writer.
     *
     * @return new xml writer
     * @see XmlWriter
     */
    @Contract(" -> new")
    public @NotNull XmlWriter newXMLWriter() {
        return new XmlWriter(this);
    }

    /**
     * Instantiates a new Xml reader.
     *
     * @param clazz         class of collection
     *                      (example, ModLinkedList.class)
     * @param builders_path path to builders
     *                      (example, "itmo.kxrxh.lab5.types.builders")
     * @return new xml reader
     * @see XmlReader
     */
    public @NotNull XmlReader newXMLReader(Class<?> clazz, String item_class, String builders_path) throws FileNotFoundException {
        return new XmlReader(this, clazz, item_class, builders_path);
    }
}
