package itmo.kxrxh.lab5.utils.xml;

import itmo.kxrxh.lab5.utils.terminal.Colors;
import itmo.kxrxh.lab5.utils.xml.exceptions.UnableToCreateFileException;

import java.io.File;
import java.io.IOException;

public class XML {
    protected final String xmlFileName;

    public XML(String xmlFileName) throws NoSuchFieldException, UnableToCreateFileException {
        File f = new File(xmlFileName);
        if (f.isDirectory() || !f.canRead()) {
            // Fixing file
            xmlFileName = "_" + xmlFileName;
            f = new File(xmlFileName);
        }
        try {
            // Trying to create new file
            if (f.createNewFile()) {
                System.out.printf("%s%s: no such file.\nCreating new file%s%n", Colors.ANSI_YELLOW, xmlFileName, Colors.ANSI_RESET);
            }
        } catch (IOException e) {
            throw new UnableToCreateFileException(e.getMessage());
        }
        this.xmlFileName = xmlFileName;
    }

    public XMLReader newReader() {
        return new XMLReader(this);
    }

    public XMLWriter newWriter() {
        return new XMLWriter(this);
    }

    public String getXmlFileName() {
        return xmlFileName;
    }
}
