package com.kalyanathagavalthalam.service;

import com.kalyanathagavalthalam.bean.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationService {
  public final static double AVERAGE_RADIUS_OF_EARTH = 6371;

  public int getDistanceInKM(Location location1, Location location2) {
      double userLat = location1.getLat();
      double userLng = location1.getLng();
      double venueLat = location2.getLat();
      double venueLng = location2.getLng();

      double latDistance = Math.toRadians(userLat - venueLat);
      double lngDistance = Math.toRadians(userLng - venueLng);

      double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
        (Math.cos(Math.toRadians(userLat))) *
          (Math.cos(Math.toRadians(venueLat))) *
          (Math.sin(lngDistance / 2)) *
          (Math.sin(lngDistance / 2));

      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

      return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
  }
}
