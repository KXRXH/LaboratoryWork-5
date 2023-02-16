package itmo.kxrxh.lab5.utils.xml_v2;

import java.io.File;

public class XML {
    protected final String xmlFileName;

    public XML(String xmlFileName) throws Exception {
        File f = new File(xmlFileName);
        if (!f.exists() || f.isDirectory()) {
            throw new Exception("File does not exist: " + xmlFileName);
        }
        this.xmlFileName = xmlFileName;
    }

    public XMLReader newReader() throws Exception {
        return new XMLReader(this);
    }

    public XMLWriter newWriter() throws Exception {
        return new XMLWriter(this);
    }

    public String getXmlFileName() {
        return xmlFileName;
    }
}
