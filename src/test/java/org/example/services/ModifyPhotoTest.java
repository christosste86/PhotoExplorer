package org.example.services;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ModifyPhotoTest {

    @Test
    void testLocatedPhotoDestinationPath(){
        ModifyPhoto modifyPhoto = new ModifyPhoto(Path.of("photos/locatedphotos/locatedPhotoTest.jpg"));
        assertEquals("U:\\BackUp\\Photos\\2024\\Winter\\cz (Jihomoravský kraj)\\okres Brno-město\\2024.3.24(20.16.6) Moje trafika (moto g71 5G) in [Brno, Vídeňská, Czechia].jpg", modifyPhoto.getDestinationPath().toString());
    }

    @Test
    void testUnplacedPhotoDestinationPath(){
        ModifyPhoto modifyPhoto = new ModifyPhoto(Path.of("photos/unplacedphoto/unplacedPhotoTest.jpg"));
        assertEquals("U:\\BackUp\\Photos\\2024\\Winter\\unplaced\\2024.2.27(11.6.41)-moto g71 5G.jpg", modifyPhoto.getDestinationPath().toString());
    }

    @Test
    void testOldPhotoDestinationPath(){
        ModifyPhoto modifyPhoto = new ModifyPhoto(Path.of("photos/oldPhotos/oldPhotoTest.JPG"));
        assertEquals("U:\\BackUp\\Photos\\2006\\Summer\\unplaced\\2006.9.9(10.32.26)-DSC-P92.jpg", modifyPhoto.getDestinationPath().toString());
    }

    @Test
    void testScannedPhotoDestinationPath(){
        ModifyPhoto modifyPhoto = new ModifyPhoto(Path.of("photos\\scennedphotos\\scannerPhotoTest.jpg"));
        assertEquals("U:\\BackUp\\Photos\\Scanned\\2024.11.23(9.27.18)-CanoScan LiDE 120.jpg", modifyPhoto.getDestinationPath().toString());
    }

}