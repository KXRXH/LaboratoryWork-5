package itmo.kxrxh.lab5.utils;

import itmo.kxrxh.lab5.collection.CollectionCore;
import itmo.kxrxh.lab5.utils.xml.XML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public final class TempFiles {
    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");

    /**
     * Methods for getting all temporary files from temp directory
     *
     * @return array of temporary files
     */
    public static File[] getTempFiles() {
        File dir = new File(TEMP_DIR);
        return dir.listFiles((d, name) -> name.startsWith("lab5") && name.endsWith(".tmp"));
    }

    public static File getLatestFile() {
        File[] files = getTempFiles();
        long maxTimeStamp = 0;
        File maxFile = null;
        for (File file : files) {
            try {
                BasicFileAttributes attr = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                if (attr.lastModifiedTime().toMillis() > maxTimeStamp) {
                    maxTimeStamp = attr.lastModifiedTime().toMillis();
                    maxFile = file;
                }
            } catch (IOException e) {
                throw new RuntimeException("Unable to read temp file attributes", e);
            }
        }
        return maxFile;
    }

    /**
     * Deletes all temporary files from temp directory
     */
    public static void deleteTempFiles() {
        for (File file : getTempFiles()) {
            file.delete();
        }
    }

    /**
     * Creates new temporary file in temp directory.
     */
    public static void createTempFile() {
        File tmpFile;
        try {
            tmpFile = File.createTempFile("lab5", ".tmp");
        } catch (IOException e) {
            throw new RuntimeException("Unable to create temporary file", e);
        }
        try {
            new XML(tmpFile).newWriter().writeCollection(CollectionCore.getCollectionManager().collection());
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Unable to save collection", e);
        }
    }
}
