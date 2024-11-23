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
}