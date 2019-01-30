package prepare;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class DataLoad {
    List<String> folderList;
    Map<String, String> keyMap;


    public DataLoad() {
        folderList = new ArrayList<String>();
        keyMap = new HashMap<String, String>();
    }

    public List<String> scanFolder(File file) {
        String[] names = file.list(new FolderFilter(Pattern.compile("[a-zA-ZА-Яа-я1-9\\s]*")));
        folderList = Arrays.asList(names);
        return folderList;
    }

    public Map<String, String> getKeys(File file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "cp1251"));
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

    public List<String> getFolderList() {
        return folderList;
    }

    public void setFolderList(List<String> folderList) {
        this.folderList = folderList;
    }
}
