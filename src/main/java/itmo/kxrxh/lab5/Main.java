package itmo.kxrxh.lab5;


import itmo.kxrxh.lab5.collection.CollectionCore;
import itmo.kxrxh.lab5.utils.TempFiles;

import java.io.File;


/**
 * @author kxrxh
 * @version 369491
 */
public final class Main {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        CollectionCore core = new CollectionCore().init();
        File tempFile = TempFiles.getLatestFile();
        if (!core.restoreCollection()) {
            core.fillOutCollection();
        }
        // Running the main loop
        core.run();
    }
}
