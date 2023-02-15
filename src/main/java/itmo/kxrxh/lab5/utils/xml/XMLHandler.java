package itmo.kxrxh.lab5.utils.xml;

@Deprecated
public abstract class XMLHandler {
    protected final XMLCore xmlCore;
    public XMLHandler(XMLCore xmlCore) {
        this.xmlCore = xmlCore;
    }
}
