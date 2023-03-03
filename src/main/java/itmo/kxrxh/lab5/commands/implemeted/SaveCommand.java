package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.Constants;
import itmo.kxrxh.lab5.commands.CollectionDependentCommand;
import itmo.kxrxh.lab5.commands.Command;
import itmo.kxrxh.lab5.utils.TempFiles;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.terminal.Colors;
import itmo.kxrxh.lab5.utils.xml.Xml;

import java.io.File;


@Command(name = "Save")
public class SaveCommand extends CollectionDependentCommand {
    @Override
    public void execute() {
        DotEnv dotEnv = new DotEnv(".env").load();
        dotEnv.load();
        Xml xmlCore;
        try {
            xmlCore = new Xml(new File(dotEnv.get(Constants.EnvironmentVariable)));
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
        System.out.println(Colors.AsciiGreen + "Collection was successfully saved." + Colors.AsciiReset);
    }
}
