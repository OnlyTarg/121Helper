package prepare;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DataLoadTest {
    DataLoad dataLoad;
    List<String> testListOfFolders;

    @Before
    public void setUp() throws Exception {
        dataLoad = new DataLoad();
        testListOfFolders = new ArrayList<String>();
    }

    @Test
    public void scanFolder() {
        testListOfFolders = dataLoad.scanFolder(new File("/"));
        assertNotNull(testListOfFolders);


    }
}