package itmo.kxrxh.lab5.utils.xml;


import itmo.kxrxh.lab5.collection.ModLinkedList;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static itmo.kxrxh.lab5.utils.string.StringUtils.toSnakeCase;

/**
 * Xml writer class. Used for writing to xml file
 *
 * @author kxrxh
 * @see XMLCore
 * @see XMLHandler
 */
public class XmlWriter extends XMLHandler {
    private final BufferedOutputStream bufferedOutput;

    protected XmlWriter(XMLCore xmlCore) {
        super(xmlCore);
        try {
            this.bufferedOutput = new BufferedOutputStream(new FileOutputStream(xmlCore.fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found");
        }
    }

    /**
     * Write string to file with new line ('\n')
     *
     * @param line String to write
     */
    protected void writeLine(String line) {
        try {
            bufferedOutput.write(line.getBytes());
            bufferedOutput.write("\n".getBytes());
            bufferedOutput.flush();

        } catch (IOException e) {
            System.out.println("Error while writing to file");
        }
    }

    /**
     * Write string to file
     *
     * @param line String to write
     */
    protected void write(String line) {
        try {
            bufferedOutput.write(line.getBytes());
            bufferedOutput.flush();

        } catch (IOException e) {
            System.out.println("Error while writing to file");
        }
    }

    private int indentLevel = 0;

    public void writeCollection(ModLinkedList collection) {
        writeLine(indentString() + "<" + collection.getClass().getSimpleName() + ">");
        indentLevel++;
        collection.forEach(this::writeObject);
        indentLevel--;
        writeLine(indentString() + "</" + collection.getClass().getSimpleName() + ">");
    }

    private void writeObject(Object object) {
        writeLine(indentString() + "<" + object.getClass().getSimpleName() + ">");
        indentLevel++;
        Arrays.stream(object.getClass().getDeclaredFields())
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        if (List.of("Address", "Coordinates", "Organization", "Product").contains(field.getType().getSimpleName())) {
                            writeObject(field.get(object));
                            return;
                        }
                        writeLine(indentString() + "<" + toSnakeCase(field.getName()) + ">" + field.get(object) + "</" + toSnakeCase(field.getName()) + ">");
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        indentLevel--;
        writeLine(indentString() + "</" + object.getClass().getSimpleName() + ">");
    }

    private String indentString() {
        return new String(new char[indentLevel * 4]).replace("\0", " ");
    }
}


