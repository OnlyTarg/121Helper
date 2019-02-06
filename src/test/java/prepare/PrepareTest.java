package prepare;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

        testListOfFolders = prepare.scanFolder(new File("/"));
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
    public void checkAndMove() throws URISyntaxException {
        /*System.out.println(postList);
        System.out.println(finalKeyMap);
        System.out.println(postFolder);*/
        //prepare.checkAndMove(postList,finalKeyMap,postFolder);
    }
}