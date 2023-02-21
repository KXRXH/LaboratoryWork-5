package itmo.kxrxh.lab5.commands.exceptions;

public class NoSuchTempDirException extends RuntimeException {
    public NoSuchTempDirException() {
        super("Unable to create temporary file.");
    }
}
