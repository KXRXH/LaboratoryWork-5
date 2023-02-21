package itmo.kxrxh.lab5.utils.xml.exceptions;

import java.nio.file.NoSuchFileException;

public class FileNameIsNullException extends NoSuchFileException {
    public FileNameIsNullException() {
        super("XML file name is null");
    }
}
