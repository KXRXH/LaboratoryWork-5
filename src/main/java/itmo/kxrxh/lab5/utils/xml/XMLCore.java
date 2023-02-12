package itmo.kxrxh.lab5.utils.xml;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.utils.Terminal.Colors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
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

    /**
     * Instantiates a new Xml core.
     *
     * @param fileName          file name
     * @param collectionManager collection manager
     */
    public XMLCore(String fileName, CollectionManager collectionManager) throws FileNotFoundException {
        if (fileName == null) {
            System.out.println(Colors.ANSI_CYAN + "File name is null. Using default file name: collection.xml" + Colors.ANSI_RESET);
            fileName = "collection.xml";
        }
        if (collectionManager == null) {
            throw new NullPointerException("Collection manager is null");
        }
        this.fileName = fileName;
        this.collectionManager = collectionManager;
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
        File file = new File(fileName);
        if (!file.exists() || file.isDirectory()) {
            throw new FileNotFoundException("File not found");
        }
        return new XmlReader(this, clazz, item_class, builders_path);
    }
}
