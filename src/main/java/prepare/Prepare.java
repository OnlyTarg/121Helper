package prepare;


import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prepare {
    int count = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getHomeFolder() {
        String fullPath = null;
        try {
            fullPath = new File(Prepare.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath().toString();
        } catch (URISyntaxException e) {
            JOptionPane.showMessageDialog(null, "Проблема при зчитуванні корневої папки " + e.getMessage());
            e.printStackTrace();
        }
        int possition = fullPath.lastIndexOf("\\");
        return fullPath.substring(0, possition);
    }

    public List<String> scanFolder(File file) {
        String[] names = file.list(new FolderFilter(Pattern.compile("[a-zA-ZА-Яа-я1-9\\sіІїЇ]*")));
        return Arrays.asList(names);
    }

    public Map<String, String> getKeysMap(String path) {
        Map<String, String> keyMap = new HashMap<String, String>();
        try {

            InputStream in = getClass().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF8"));
            String s = "";
            while ((s = reader.readLine()) != null) {
                String[] temp = s.split("::");
                keyMap.put(temp[0].trim(), temp[1].trim());
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

    public List<String> getNamesFromFolder(File folder) {
        if (folder.exists()) {
            String[] names = folder.list();
            return Arrays.asList(names);
        } else {
            JOptionPane.showMessageDialog(null, "Папка \"ПОШТА\" не знайдена");
            List<String> exeptionList = new ArrayList<String>();
            exeptionList.add("Папка не знайдена");
            return exeptionList;
        }


    }

    public void checkAndMove(List<String> postlist, Map<String, String> finalKeymap, File postFolder) throws URISyntaxException {
        Set<String> keys = finalKeymap.keySet();
        for (String filename :
                postlist) {


            for (String key :
                    keys) {

                Pattern[] regex = makePatternArray(finalKeymap.get(key));
                for (int i = 0; i < regex.length; i++) {


                    if (regex[i].matcher(filename).find()) {
                        File file = new File(postFolder + "\\" + filename);
                        File targetfolder = new File(getHomeFolder() + "\\" + key);
                        File targetFile = new File(getHomeFolder() + "\\" + key + "\\" + filename);
                        if (!targetFile.exists()) {
                            file.renameTo(targetFile);
                            count++;
                        } else {
                            String latestDublicate = findLatestNumberOfExistingCopy(targetfolder, filename);
                            if (numberOdDublicate(filename) > 0) {
                                File newFile = new File(getHomeFolder() + "\\" + key + "\\" + latestDublicate);
                                file.renameTo(newFile);
                                count++;
                                continue;
                            }




                            file.renameTo(new File(targetfolder + "\\" + renameDublicate(latestDublicate, latestDublicate)));
                            count++;
                        }

                    }
                }

            }

        }
    }

    public String renameDublicate(String fileName, String latestdublicate) {

        int latestdublicat = numberOdDublicate(latestdublicate);
        int numberOfDublicate = numberOdDublicate(fileName);
        int indexofLastDot = fileName.lastIndexOf('.');
        String firstPartOfName = fileName.substring(0, indexofLastDot);
        String secondPartOfName = fileName.substring(indexofLastDot + 1);
        if (numberOfDublicate > 0) {
            firstPartOfName = firstPartOfName.substring(0, firstPartOfName.lastIndexOf('('));
            return firstPartOfName + "(" + (++latestdublicat) + ")." + secondPartOfName;
        } else {
            return firstPartOfName + "(1)." + secondPartOfName;

        }

    }

    // Sf some dublicats of given file exist, this method return last number of existing file
    // Example: app attempt to allocate file Pavel.doc
    // if target folder already consist one or more copies of this file (like Pavel(2).doc and Pavel(3).doc) this method will return
    // number of latest copy. In this case is "3".
/*    public Integer findLatestNumberOfExistingCopy(File folder, String originalString) {
        int countOfDublicate = -1;
        String firstPartOfOriginalString = originalString.substring(0,originalString.lastIndexOf('.'));
        String[] fileList = folder.list();
        for (String name :
                fileList) {
            if (name.matches(firstPartOfOriginalString+"\\(\\d+\\)..*")){
                int currentCoun = numberOdDublicate(name);
                if (countOfDublicate < currentCoun) {
                    countOfDublicate = currentCoun;
                }
            }
        }
        return countOfDublicate;
    }*/
    public String findLatestNumberOfExistingCopy(File folder, String originalString) {
        String result = originalString;
        int countOfDublicate = -1;
        String temp = originalString.substring(0, originalString.lastIndexOf('.'));
        String firstPartOfOriginalString = "";
        if(numberOdDublicate(originalString)>0) {
             firstPartOfOriginalString = temp.substring(0, originalString.lastIndexOf('('));
        }
        else{
            firstPartOfOriginalString = temp;
        }


            String[] fileList = folder.list();
            for (String name :
                    fileList) {
                if (name.matches(firstPartOfOriginalString + "\\(\\d+\\)..*")) {
                    int currentCoun = numberOdDublicate(name);
                    if (countOfDublicate < currentCoun) {
                        countOfDublicate = currentCoun;
                        String temp1 = name.substring(0, originalString.lastIndexOf('.'));
                        String temp2 = originalString.substring(originalString.lastIndexOf('.'));
                        String firstPart = "";
                        String rezultname = "";
                        if (numberOdDublicate(originalString) > 0) {
                            firstPart = temp1.substring(0, originalString.lastIndexOf('('));
                            rezultname = firstPart + "(" + ++currentCoun + ")" + temp2;
                        }
                        else{
                            firstPart = temp1.substring(0,originalString.lastIndexOf('.'));
                            rezultname = firstPart + "(" + currentCoun + ")" + temp2;
                        }
                        result = rezultname;
                    }
                }
            }

        return result;
    }


    public Integer numberOdDublicate(String s) {

        int numberOfDublicate = 0;
        char charBeforeDot = s.charAt(s.lastIndexOf('.') - 1);
        if (charBeforeDot == ')') {
            String str = s.substring(0, s.lastIndexOf(')'));
            if (str.lastIndexOf('(') > 0) {
                String digit = str.substring(str.lastIndexOf('(') + 1);
                if (isNumeric(digit)) {
                    numberOfDublicate = Integer.valueOf(digit);
                    return numberOfDublicate;
                }
            }


        }
        return numberOfDublicate;
    }


    private Pattern[] makePatternArray(String str) {
        String[] keyarray = str.split(":");
        Pattern[] patternArray = new Pattern[keyarray.length];
        for (int i = 0; i < keyarray.length; i++) {
            patternArray[i] = Pattern.compile(keyarray[i].trim());
        }
        return patternArray;
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

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
