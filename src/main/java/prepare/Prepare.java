package prepare;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Pattern;

public class Prepare {

    public String getHomeFolder()  {
        String fullPath = null;
        try {
            fullPath = new File(Prepare.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath().toString();
        } catch (URISyntaxException e) {
            JOptionPane.showMessageDialog(null,"Проблема при зчитуванні корневої папки " + e.getMessage());
            e.printStackTrace();
        }
        int possition = fullPath.lastIndexOf("\\");
        return fullPath.substring(0, possition);
    }

    public List<String> scanFolder(File file) {
        String[] names = file.list(new FolderFilter(Pattern.compile("[a-zA-ZА-Яа-я1-9\\s]*")));
        return Arrays.asList(names);
    }
    public Map<String, String> getKeysMap(String path) {
        Map<String, String> keyMap = new HashMap<String, String>();
        try {

            InputStream in = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF8"));
            String s = "";
            while ((s = reader.readLine()) != null) {
                String[] temp = s.split(":");
                keyMap.put(temp[0], temp[1]);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return keyMap;
    }
    public Map<String, String> compareLists(List<String> folderList, Map<String, String> keyMap) {

        Map<String, String> rezult = new HashMap<String, String>();

        for (int i = 0; i < folderList.size(); i++) {
            String folderName = folderList.get(i);
            if (keyMap.containsKey(folderName)) {
                rezult.put(folderName, keyMap.get(folderName));
            }
        }
        return rezult;
    }

    public static class FolderFilter implements FilenameFilter {
        Pattern pattern;

        public FolderFilter(Pattern pattern) {
            this.pattern = pattern;
        }


        public boolean accept(File dir, String name) {
            return pattern.matcher(name).matches();
        }
    }

}
