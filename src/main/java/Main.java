import prepare.Prepare;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static final String KEYS_TXT = "/keys.txt";
    static Prepare prepare;
    Map<String, String> keysMAP;
    static File homefolder;
    static String folderPOSHTA = "\\ПОШТА";
    static List<String> postList;

    static {
        prepare = new Prepare();
        homefolder = new File(prepare.getHomeFolder());
    }

    public static void main(String[] args) throws URISyntaxException {

        List<String> listOfFolders = prepare.scanFolder(new File(prepare.getHomeFolder()));
        HashMap<String, String> finalKeyMap = (HashMap<String, String>) prepare.compareLists(listOfFolders, prepare.getKeysMap(KEYS_TXT));


        //fot test
        JOptionPane.showMessageDialog(null, prepare.getKeysMap(KEYS_TXT));
        postList =  prepare.getNamesFromFolder(new File(homefolder + folderPOSHTA));
        System.out.println(postList);


    }
}

//fot test
//JOptionPane.showMessageDialog(null,"Знайдено " + prepare.getFolderList().size() + " папки" );