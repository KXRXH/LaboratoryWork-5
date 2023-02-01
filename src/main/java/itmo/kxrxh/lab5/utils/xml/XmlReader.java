package itmo.kxrxh.lab5.utils.xml;

import java.util.Scanner;

/**
 * Xml reader class.
 * <p>
 * Used for reading from xml file
 *
 * @author kxrxh
 * @see XMLCore
 * @see XMLHandler
 */
public class XmlReader extends XMLHandler {
    public final Scanner scanner;

    protected XmlReader(XMLCore xmlCore) {
        super(xmlCore);
        this.scanner = new Scanner(xmlCore.fileName);
    }
}
