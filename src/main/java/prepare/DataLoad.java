package prepare;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DataLoad {
    List<String> folderList;

    public DataLoad() {
        this.folderList = new ArrayList<String>();
    }

 public List<String> scanFolder(File file){
     String[] names = file.list(new FolderFilter(Pattern.compile("[a-zA-ZА-Яа-я1-9\\s]*")));
     folderList = Arrays.asList(names);
     return folderList;
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
