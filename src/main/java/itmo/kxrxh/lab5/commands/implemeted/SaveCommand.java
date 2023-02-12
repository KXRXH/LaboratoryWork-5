package itmo.kxrxh.lab5.commands.implemeted;

import itmo.kxrxh.lab5.collection.manager.CollectionManager;
import itmo.kxrxh.lab5.commands.CollectionDependent;
import itmo.kxrxh.lab5.commands.Executable;
import itmo.kxrxh.lab5.utils.Terminal.Colors;
import itmo.kxrxh.lab5.utils.env.dotenv.DotEnv;
import itmo.kxrxh.lab5.utils.xml.XMLCore;

import java.io.FileNotFoundException;

import static itmo.kxrxh.lab5.Main.ENVIRONMENT_VARIABLE;


public class SaveCommand extends CollectionDependent implements Executable {
    public SaveCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute() {
        DotEnv dotEnv = new DotEnv(".env").load();
        dotEnv.load();
        XMLCore xmlCore;
        try {
            xmlCore = new XMLCore(dotEnv.get(ENVIRONMENT_VARIABLE), collectionManager);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        xmlCore.newXMLWriter().writeCollection(collectionManager.collection());
        System.out.println(Colors.ANSI_GREEN + "Collection was successfully saved." + Colors.ANSI_RESET);
    }
}
