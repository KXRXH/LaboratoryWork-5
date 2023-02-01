package itmo.kxrxh.lab5.utils.xml;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
     * @return new xml reader
     * @see XmlReader
     */
    @Contract(" -> new")
    public @NotNull XmlReader newXMLReader() {
        return new XmlReader(this);
    }
}
