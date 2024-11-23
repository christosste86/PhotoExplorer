package org.example.connections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileTest {
    @Test
    void json(){
        JsonFile jsonFile = new JsonFile();
        assertEquals("U:\\BackUp\\Photos", jsonFile.getFileExplorerTargetDirectory());
        assertEquals("U:\\Photos", jsonFile.getFileExplorerSourceDirectory());
        assertArrayEquals(new String[]{"CanoScan LiDE 120"}, jsonFile.getScannerModels());
    }

}