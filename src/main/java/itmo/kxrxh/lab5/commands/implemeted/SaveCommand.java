package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.terminal.Colors;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.xml.XML;

import static itmo.kxrxh.lab5.Main.ENVIRONMENT_VARIABLE;


public class SaveCommand extends CollectionDependent implements Executable {
    public SaveCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        DotEnv dotEnv = new DotEnv(".env").load();
        dotEnv.load();
        XML xmlCore;
        try {
            xmlCore = new XML(dotEnv.get(ENVIRONMENT_VARIABLE));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            xmlCore.newWriter().writeCollection(collectionManager.collection());
        } catch (Exception e) {
            System.err.println("Unable to save collection");
            return;
        }
        System.out.println(Colors.ANSI_GREEN + "Collection was successfully saved." + Colors.ANSI_RESET);
    }
}
