package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.Constants;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.utils.TempFiles;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.terminal.Colors;
import itmo.kxrxh.lab5.utils.xml.XML;

import java.io.File;


public class SaveCommand extends CollectionDependent {
    @Override
    public void execute() {
        DotEnv dotEnv = new DotEnv(".env").load();
        dotEnv.load();
        XML xmlCore;
        try {
            xmlCore = new XML(new File(dotEnv.get(Constants.ENVIRONMENT_VARIABLE)));
        } catch (Exception e) {
            throw new RuntimeException("Unable to save collection", e);
        }
        try {
            xmlCore.newWriter().writeCollection(collectionManager.collection());
            // Deleting all temporary files
            TempFiles.deleteTempFiles();
        } catch (Exception e) {
            System.err.println("Unable to save collection: " + e.getMessage());
            return;
        }
        System.out.println(Colors.ANSI_GREEN + "Collection was successfully saved." + Colors.ANSI_RESET);
    }
}
