package itmo.kxrxh.lab5;


import itmo.kxrxh.lab5.collection.CollectionCore;


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
        if (!core.restoreCollection()) {
            core.fillOutCollection();
        }
        // Running the main loop
        core.run();
    }
}
