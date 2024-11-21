package org.example.connections.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeocodeServiceTest {

    @Test
    void nullGeocode() {
        GeocodeService geocodeService = new GeocodeService(null, null);
        assertNull(geocodeService.getLocationObject().getLatitude());
        assertNull(geocodeService.getLocationObject().getLongitude());
        assertNull(geocodeService.getLocationObject().getCountry_code());
        assertNull(geocodeService.getLocationObject().getCountry());
        assertNull(geocodeService.getLocationObject().getState());
        assertNull(geocodeService.getLocationObject().getPostcode());
        assertNull(geocodeService.getLocationObject().getCounty());
        assertNull(geocodeService.getLocationObject().getMunicipality());
        assertNull(geocodeService.getLocationObject().getCity());
        assertNull(geocodeService.getLocationObject().getVillage());
        assertNull(geocodeService.getLocationObject().getSuburb());
        assertNull(geocodeService.getLocationObject().getRoad());
        assertNull(geocodeService.getLocationObject().getHouse_number());
        assertNull(geocodeService.getLocationObject().getShop());
        assertNull(geocodeService.getLocationObject().getTourism());
    }
    @Test
    void athensGeocode() {
        //https://geocode.maps.co/reverse?lat=37.983810&lon=23.727539&api_key=672494c88b52e639833548cvjf55303
        GeocodeService geocodeService = new GeocodeService(37.9836635, 23.727484);
        assertEquals(37.9836635,geocodeService.getLocationObject().getLatitude());
        assertEquals(23.727484,geocodeService.getLocationObject().getLongitude());
        assertEquals("gr",geocodeService.getLocationObject().getCountry_code());
        assertEquals("Greece",geocodeService.getLocationObject().getCountry());
        assertEquals("Attica",geocodeService.getLocationObject().getState());
        assertEquals("104 31",geocodeService.getLocationObject().getPostcode());
        assertEquals("Regional Unit of Central Athens",geocodeService.getLocationObject().getCounty());
        assertEquals("Municipality of Athens",geocodeService.getLocationObject().getMunicipality());
        assertEquals("Athens",geocodeService.getLocationObject().getCity());
        assertNull(geocodeService.getLocationObject().getVillage());
        assertNull(geocodeService.getLocationObject().getSuburb());
        assertEquals("Αθηνάς",geocodeService.getLocationObject().getRoad());
        assertNull(geocodeService.getLocationObject().getHouse_number());
        assertNull(geocodeService.getLocationObject().getShop());
        assertNull(geocodeService.getLocationObject().getTourism());
        assertEquals("Μέγας Αλέξανδρος", geocodeService.getLocationObject().getBuilding());
    }
}