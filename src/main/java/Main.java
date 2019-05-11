import prepare.Prepare;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static final String KEYS_TXT = "/keys.txt";
    static Prepare prepare;
    Map<String, String> keysMAP;
    static File homefolder;
    static File postFolder;
    static List<String> postList;

    static {
        prepare = new Prepare();
        homefolder = new File(prepare.getHomeFolder());
        postFolder = new File(homefolder + "\\ПОШТА");
    }

    public static void main(String[] args) throws URISyntaxException {
        List<String> listOfFolders = prepare.findFolders(new File(prepare.getHomeFolder()));
        HashMap<String, String> finalKeyMap = (HashMap<String, String>) prepare.compareLists(listOfFolders, prepare.getKeysMap(KEYS_TXT));
        postList = prepare.getNamesFromFolder(postFolder);
        prepare.checkAndMove(postList, finalKeyMap, postFolder);
        JOptionPane.showMessageDialog(null, "Переміщено " + prepare.getMovedFiles() + " файлів");
    }
}
