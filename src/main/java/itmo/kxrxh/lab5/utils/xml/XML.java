package itmo.kxrxh.lab5.utils.xml;

import java.io.File;

public class XML {
    protected final File xmlFile;

    public XML(File xmlFile) throws NoSuchFieldException {
        if (xmlFile.isDirectory() || !xmlFile.canRead()) {
            throw new NoSuchFieldException("No such file or directory: " + xmlFile.getAbsolutePath());
        }
        this.xmlFile = xmlFile;
    }

    public XMLReader newReader() {
        return new XMLReader(this);
    }

    public XMLWriter newWriter() {
        return new XMLWriter(this);
    }

    public File getXmlFile() {
        return xmlFile;
    }
}
