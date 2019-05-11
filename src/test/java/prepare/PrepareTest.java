package prepare;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PrepareTest {
    Prepare prepare;

    List<String> testListOfFolders;
    Map<String, String> keyMap;
    List<String> postList;
    Map <String,String> finalKeyMap;
    File postFolder;

    @Before
    public void setUp() throws Exception {
        prepare = new Prepare();
        keyMap = new HashMap<String, String>();
        testListOfFolders = new ArrayList<String>();
        testListOfFolders.add("Новая Папка0");
        testListOfFolders.add("Новая Папка1");
        testListOfFolders.add("Новая Папка2");
        testListOfFolders.add("Новая Папка3");
        keyMap.put("Новая Папка0", "someKey1");
        keyMap.put("Новая Папка1", "someKey1");

        postList = new ArrayList<String>();
        postList.add("exampleOfTxt.txt");
        postList.add("exampleOfDoc.doc");
        postList.add("exampleOfMp3.doc");

        finalKeyMap = new HashMap<String, String>();
        finalKeyMap.put("TXT", "Txt");
        finalKeyMap.put("DOC", "Doc");
        finalKeyMap.put("MP3", "Mp3");


        postFolder = new File(Prepare.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()+"\\ПОШТА");



    }

    @Test
    public void scanFolder() {

        testListOfFolders = prepare.findFolders(new File("/"));
        assertNotNull(testListOfFolders);


    }

    @Test
    public void compareLists() {
        HashMap<String, String> testList = (HashMap<String, String>) prepare.compareLists(testListOfFolders, keyMap);
        assertEquals(2, testList.size());


    }

    @Test
    public void findPathOfNeededFolder() {
        String path = prepare.getHomeFolder();
        assertNotNull(path);
    }

    @Test
    public void getNamesFromFolder() {
        File file = mock(File.class);
        when(file.exists()).thenReturn(true);
        when(file.list()).thenReturn(new String[]{"A","B","C"});
        List<String> testList = prepare.getNamesFromFolder(file);
        assertEquals(3, testList.size());
    }

    @Test
@Ignore
    public void checkAndMove() throws URISyntaxException {
        System.out.println(postList);
        System.out.println(finalKeyMap);
        System.out.println(postFolder);
        File file = mock(File.class);
        String[] folderlist = {"Pavlik(257).doc","Pavlik(256).doc", "Olha.doc", "Bereza.zip"};
        when(file.list()).thenReturn(folderlist);
        prepare.checkAndMove(postList,finalKeyMap,postFolder);

        //prepare.checkAndMove(postList,finalKeyMap,postFolder);
    }

    @Test
    public void numberOdDublicate() {
        String testString = "(34585jk((25).doc";
        Prepare prepare = new Prepare();
        int i = prepare.numberOfDublicate(testString);
        assertEquals(25, i);
    }

   /* @Test

    public void renameDublicate() {
        Prepare prepare = new Prepare();
        String str = prepare.renameDublicate("Pavlik1(1).doc",1);
        assertEquals(str,"Pavlik1(2).doc");
    }*/

    @Test
    public void isDublicateExist() {
        /*String originalfileName = "Pavlik.doc";
        String firstPartOfOriginalString = originalfileName.substring(0,originalfileName.lastIndexOf('.'));
        System.out.println("firstpart = " + firstPartOfOriginalString);
        String testString = "Pavlik(2).doc";

        String regex = firstPartOfOriginalString+"\\(\\d\\)..*";
        Pattern pattern = Pattern.compile("Pavlik\\(\\d+\\)..*");
        System.out.println("regex = " + regex);
        if(testString.matches(regex)){
            System.out.println("Good");
        }
        else {
            System.out.println("Fail");
        }*/
        /*File file = mock(File.class);
        String[] folderlist = {"Pavlik(257).doc","Pavlik(256).doc", "Olha.doc", "Bereza.zip"};
        when(file.list()).thenReturn(folderlist);
        int rezult = prepare.findLatestNumberOfExistingCopy(file, "Pavlik.doc");
        assertEquals(257,rezult);*/


    }

    @Test
    public void getHomeFolder() {
        assertNotNull(prepare.getHomeFolder());

    }

    @Test
    public void findFolders() {
        String[] testFolder = {"Папка1","2323","Папка3","Папка4","1.doc","some.rar","Витяг.doc" };
        File testFile = mock(File.class);
        when(testFile.list()).thenReturn(testFolder);
        List<String> foldersList = prepare.findFolders(testFile);
        assertNotNull(foldersList);
        assertEquals(4,foldersList.size());


    }
}