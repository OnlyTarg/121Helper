package prepare;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PrepareTest {
    Prepare prepare;
    List<String> testListOfFolders;
    Map<String, String> keyMap;

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


    }

    @Test
    public void scanFolder() {

        testListOfFolders = prepare.scanFolder(new File("/"));
        assertNotNull(testListOfFolders);


    }

    @Test
    public void compareLists() {
       HashMap<String,String> testList = (HashMap<String, String>) prepare.compareLists(testListOfFolders, keyMap);
        assertEquals(2, testList.size());


    }

    @Test
    public void findPathOfNeededFolder() {
        String path = prepare.getHomeFolder();
        assertNotNull(path);
    }
}