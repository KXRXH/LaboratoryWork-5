package itmo.kxrxh.lab5;


import itmo.kxrxh.lab5.collection.CollectionCore;


/**
 * @author kxrxh
 * @version 369491
 */
public final class Main {

    /**
     * The constant ENVIRONMENT_VARIABLE - name of environment variable, which contains file name
     */
    public static final String ENVIRONMENT_VARIABLE = "FILE_NAME";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        CollectionCore core = new CollectionCore().init().fillOutCollection();
        // Running the main loop
        core.run();
    }
}
