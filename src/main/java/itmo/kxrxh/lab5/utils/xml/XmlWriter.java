package itmo.kxrxh.lab5.utils.xml;


import java.io.*;

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
    public void writeLine(String line) {
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
    public void write(String line) {
        try {
            bufferedOutput.write(line.getBytes());
            bufferedOutput.flush();

        } catch (IOException e) {
            System.out.println("Error while writing to file");
        }
    }
}
