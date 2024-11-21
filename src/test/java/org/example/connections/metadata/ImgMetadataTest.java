package org.example.connections.metadata;

import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class ImgMetadataTest {

    @Test
    void locatedImg() {
        ImgMetadata locatedImage = new ImgMetadata("locatedImg.jpg");
        assertEquals(49.17907397222222,locatedImage.getLatitude());
        assertEquals(16.595955,locatedImage.getLongitude());
        assertEquals(4080,locatedImage.getWidth());
        assertEquals(3072,locatedImage.getHeight());
        assertEquals("moto g71 5G",locatedImage.getModel());
        assertNull(locatedImage.getCameraOwner());
        assertNull(locatedImage.getArtist());
        assertNull(locatedImage.getBodySerialNumber());
        assertNull(locatedImage.getDateTimeOriginal());
        assertEquals(2024,locatedImage.getDateTime().getYear());
        assertNull(locatedImage.getDateTimeDigital());
        assertNull(locatedImage.getHostname());
    }

    @Test
    void unbplacedImg() {
        ImgMetadata locatedImage = new ImgMetadata("unplacedImg.jpg");
        assertNull(locatedImage.getLatitude());
        assertNull(locatedImage.getLongitude());
        assertEquals(4080,locatedImage.getWidth());
        assertEquals(3072,locatedImage.getHeight());
        assertEquals("moto g71 5G",locatedImage.getModel());
        assertNull(locatedImage.getCameraOwner());
        assertNull(locatedImage.getArtist());
        assertNull(locatedImage.getBodySerialNumber());
        assertNull(locatedImage.getDateTimeOriginal());
        assertEquals(2024,locatedImage.getDateTime().getYear());
        assertNull(locatedImage.getDateTimeDigital());
        assertNull(locatedImage.getHostname());
    }
}