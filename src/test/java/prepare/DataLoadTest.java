package prepare;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataLoadTest {
    DataLoad dataLoad;
    List<String> testListOfFolders;
    Map<String, String> keyMap;

    @Before
    public void setUp() throws Exception {
        dataLoad = new DataLoad();
        keyMap = new HashMap<String, String>();
        testListOfFolders = new ArrayList<String>();
        testListOfFolders.add("Новая Папка0");
        testListOfFolders.add("Новая Папка1");
        testListOfFolders.add("Новая Папка2");
        testListOfFolders.add("Новая Папка3");
        keyMap.put("Новая Папка0", "someKey1");
        keyMap.put("Новая Папка1", "someKey1");


    }

    @Test
    public void scanFolder() {
        testListOfFolders = dataLoad.scanFolder(new File("/"));
        assertNotNull(testListOfFolders);


    }

    @Test
    public void compareLists() {
       HashMap<String,String> testList = (HashMap<String, String>) dataLoad.compareLists(testListOfFolders, keyMap);
        assertEquals(2, testList.size());


    }
}