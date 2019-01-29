import prepare.DataLoad;

import java.io.File;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        DataLoad dataLoad = new DataLoad();
        dataLoad.scanFolder(new File("/"));
        for (String name :
                dataLoad.getFolderList()) {
            System.out.println(name);
        }

        Map<String,String> keys = dataLoad.getKeys(new File("keys.txt"));
        System.out.println(keys.entrySet());
    }
}
