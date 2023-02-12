package itmo.kxrxh.lab5;


import itmo.kxrxh.lab5.collection.CollectionCore;


/**
 * Main class
 *
 * @author kxrxh
 * @version 369491
 */
public final class Main {
    public static final String ENVIRONMENT_VARIABLE = "FILE_NAME";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        CollectionCore core = new CollectionCore().init().fillOutCollection();
        core.run();
    }
}
