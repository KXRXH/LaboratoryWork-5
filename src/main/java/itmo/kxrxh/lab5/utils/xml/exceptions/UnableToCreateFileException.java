package itmo.kxrxh.lab5.utils.xml.exceptions;

public class UnableToCreateFileException extends Exception {
    public UnableToCreateFileException(String message) {
        super("Unable to create file: " + message);
    }
}
