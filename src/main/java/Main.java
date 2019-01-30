import prepare.DataLoad;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        DataLoad dataLoad = new DataLoad();
        dataLoad.scanFolder(new File("/"));
        Map<String,String> keys = dataLoad.getKeys(new File("keys.txt"));
        HashMap<String,String> list = (HashMap<String, String>) dataLoad.compareLists(dataLoad.getFolderList(), keys);
        System.out.println(list);
    }
}
