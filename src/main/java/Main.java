import prepare.DataLoad;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Main {



    public static void main(String[] args) throws URISyntaxException {
        DataLoad dataLoad = new DataLoad();
        //fot test
        //JOptionPane.showMessageDialog(null, dataLoad.getHomeFolder());
        dataLoad.scanFolder(new File(dataLoad.getHomeFolder()));

        Map<String,String> keys = dataLoad.getKeys(new File("keys.txt"));
        HashMap<String,String> list = (HashMap<String, String>) dataLoad.compareLists(dataLoad.getFolderList(), keys);
        System.out.println(list);
        //fot test
        JOptionPane.showMessageDialog(null,"Знайдено " + dataLoad.getFolderList().size() + " папки" );
    }
}
