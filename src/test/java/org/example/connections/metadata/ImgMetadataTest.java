package org.example.connections.metadata;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ImgMetadataTest {
    @Test
    void testUnplacedPhoto(){
        ImgMetadata imgMetadata = new ImgMetadata(Path.of("unplacedPhotoTest.jpg"));
        assertNull(imgMetadata.getLongitude());
        assertNull(imgMetadata.getLatitude());
        assertEquals(2024,imgMetadata.getDateTime().getYear());
        assertEquals(4080, imgMetadata.getWidth());
        assertEquals(3072, imgMetadata.getHeight());
        assertNull(imgMetadata.getCameraOwner());
        assertEquals("moto g71 5G", imgMetadata.getModel());
        assertNull(imgMetadata.getArtist());
        assertNull(imgMetadata.getBodySerialNumber());
        assertNull(imgMetadata.getDateTimeOriginal());
        assertEquals(2024, imgMetadata.getModificationDate().getYear());
        assertNull(imgMetadata.getDateTimeDigital());
        assertNull(imgMetadata.getHostname());
    }

    @Test
    void testLocatedPhoto(){
        ImgMetadata imgMetadata = new ImgMetadata(Path.of("locatedPhotoTest.jpg"));
        assertEquals(16.595955, imgMetadata.getLongitude());
        assertEquals(49.17907397222222, imgMetadata.getLatitude());
        assertEquals(2024,imgMetadata.getDateTime().getYear());
        assertEquals(4080, imgMetadata.getWidth());
        assertEquals(3072, imgMetadata.getHeight());
        assertNull(imgMetadata.getCameraOwner());
        assertEquals("moto g71 5G", imgMetadata.getModel());
        assertNull(imgMetadata.getArtist());
        assertNull(imgMetadata.getBodySerialNumber());
        assertNull(imgMetadata.getDateTimeOriginal());
        assertEquals(2024, imgMetadata.getModificationDate().getYear());
        assertNull(imgMetadata.getDateTimeDigital());
        assertNull(imgMetadata.getHostname());
    }

    @Test
    void testOldPhoto(){
        ImgMetadata imgMetadata = new ImgMetadata(Path.of("oldPhotoTest.JPG"));
        assertNull(imgMetadata.getLongitude());
        assertNull(imgMetadata.getLatitude());
        assertEquals(2006,imgMetadata.getDateTime().getYear());
        assertEquals(1280, imgMetadata.getWidth());
        assertEquals(960, imgMetadata.getHeight());
        assertNull(imgMetadata.getCameraOwner());
        assertEquals("DSC-P92", imgMetadata.getModel());
        assertNull(imgMetadata.getArtist());
        assertNull(imgMetadata.getBodySerialNumber());
        assertNull(imgMetadata.getDateTimeOriginal());
        assertEquals(2024, imgMetadata.getModificationDate().getYear());
        assertNull(imgMetadata.getDateTimeDigital());
        assertNull(imgMetadata.getHostname());
    }

    @Test
    void testScannerPhoto(){
        ImgMetadata imgMetadata = new ImgMetadata(Path.of("scannerPhotoTest.jpg"));
        assertNull(imgMetadata.getLongitude());
        assertNull(imgMetadata.getLatitude());
        assertNull(imgMetadata.getDateTime());
        assertEquals(1776, imgMetadata.getWidth());
        assertEquals(1187, imgMetadata.getHeight());
        assertNull(imgMetadata.getCameraOwner());
        assertEquals("CanoScan LiDE 120", imgMetadata.getModel());
        assertNull(imgMetadata.getArtist());
        assertNull(imgMetadata.getBodySerialNumber());
        assertNull(imgMetadata.getDateTimeOriginal());
        assertEquals(2024, imgMetadata.getModificationDate().getYear());
        assertNull(imgMetadata.getDateTimeDigital());
        assertNull(imgMetadata.getHostname());
    }

}