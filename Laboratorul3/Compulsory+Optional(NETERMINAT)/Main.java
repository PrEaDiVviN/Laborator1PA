package com.company;

import java.time.LocalTime;

/**
 * Partea de optional nu este terminata. Va fi realizata pana la ora urmatoare.
 *
 * @author Hutu Alexandru Dumitru
 */

public class Main {

    public static void main(String[] args) {
        Hotel v1 = new Hotel("Hotel", "Best hotel", "hotel.png", 12.6, 130.6, 1);
        Museum v2 = new Museum("Museum A", "Best Museum", 126.5, 13.6, "museumA.png", LocalTime.of(9, 30), LocalTime.parse("17:00"), 20);
        Museum v3 = new Museum("Museum B", "Almost best museum", 126.5, 150.6, "museumB.png", LocalTime.of(8, 30), LocalTime.parse("14:00"), 19);
        Church v4 = new Church();
        v4.setOpeningTime(LocalTime.of(7, 30));
        v4.setClosingTime(LocalTime.MIDNIGHT);
        v4.setName("Church A");
        v4.setDescription("Catholic church");
        v4.setLatitude(125.6);
        v4.setLongitude(12.7);
        Church v5 = new Church();
        v5.setOpeningTime(LocalTime.of(6, 30));
        v5.setClosingTime(LocalTime.MIDNIGHT);
        v5.setName("Church B");
        v5.setDescription("Orthodox church");
        v5.setLatitude(155.6);
        v5.setLongitude(14.7);
        Restaurant v6 = new Restaurant("Restaurant", "Best restaurant", "restaurant.href", 124.3, 135.4, 1);
        v1.place = 0;
        v2.place = 1;
        v3.place = 2;
        v4.place = 3;
        v5.place = 4;
        v6.place = 5;
        v1.setCost(v2, 30);
        v1.setCost(v2, 50);
        v2.setCost(v3, 20);
        v2.setCost(v4, 20);
        v2.setCost(v5, 10);
        v3.setCost(v4, 20);
        v4.setCost(v5, 30);
        v4.setCost(v6, 10);
        v5.setCost(v6, 20);
        /*
        City city = new City("Iasi");
        city.addLocation(v1);
        city.addLocation(v2);
        city.addLocation(v3);
        city.addLocation(v4);
        city.addLocation(v5);
        city.addLocation(v6);
        city.displayVisitableAndNotPayable();
        TravelPlan travel = new TravelPlan(city);
        travel.addPreference(2);travel.addPreference(3);travel.addPreference(4);travel.addPreference(5);travel.addPreference(6);
        travel.getBestTravelPlan();
    */
    }
}
