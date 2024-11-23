package org.example.connections.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class GeocodeServiceTest {
  @Test
    void nullTest(){
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
  void locatedTest(){
    GeocodeService geocodeService = new GeocodeService(37.969432299999994, 23.72236550000001);
    assertEquals(37.969432299999994,geocodeService.getLocationObject().getLatitude());
    assertEquals(23.72236550000001, geocodeService.getLocationObject().getLongitude());
    assertEquals("gr",geocodeService.getLocationObject().getCountry_code());
    assertEquals("Greece", geocodeService.getLocationObject().getCountry());
    assertEquals("Attica", geocodeService.getLocationObject().getState());
    assertEquals("117 42",geocodeService.getLocationObject().getPostcode());
    assertEquals("Regional Unit of Central Athens",geocodeService.getLocationObject().getCounty());
    assertEquals("Municipality of Athens",geocodeService.getLocationObject().getMunicipality());
    assertEquals("Athens",geocodeService.getLocationObject().getCity());
    assertNull(geocodeService.getLocationObject().getVillage());
    assertEquals("Πετράλωνα", geocodeService.getLocationObject().getSuburb());
    assertEquals("Ροβέρτου Γκάλλι", geocodeService.getLocationObject().getRoad());
    assertEquals("43", geocodeService.getLocationObject().getHouse_number());
    assertNull(geocodeService.getLocationObject().getShop());
    assertNull(geocodeService.getLocationObject().getTourism());

  }
}