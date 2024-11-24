package org.example.connections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonFileTest {
    @Test
    void json(){
        JsonFile jsonFile = new JsonFile();
        assertEquals("U:\\BackUp\\Photos", jsonFile.getFileExplorerTargetDirectory());
        assertEquals("U:\\Photos", jsonFile.getFileExplorerSourceDirectory());
        assertEquals("CanoScan LiDE 120", jsonFile.getScannerModels().get(0));
        assertEquals("192.168.1.13", jsonFile.getFtpClientServer());
        assertEquals(2221, jsonFile.getFtpClientPort());
        assertEquals("christos", jsonFile.getFtpClientUser());
        assertEquals("android", jsonFile.getFtpClientPass());
        assertEquals("/DCIM/Camera", jsonFile.getFtpTransferDirPath());
        assertEquals("U:\\BackUp\\Photos", jsonFile.getFtpTransferDestinationDirPath());
        assertFalse(jsonFile.getFtpTransferRemoveFileFromFtp());
    }
}