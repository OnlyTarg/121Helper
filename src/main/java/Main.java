import prepare.DataLoad;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        DataLoad dataLoad = new DataLoad();
        dataLoad.scanFolder(new File("/"));
        for (String name :
                dataLoad.getFolderList()) {
            System.out.println(name);
        }
    }
}
