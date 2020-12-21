package com.gridnine.testing;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;




public class Main {

    public static void main(String[] args) {
//        List<com.gridnine.testing.DefaultFilter> defaultFilters = new ArrayList<>();
//        defaultFilters.add(com.gridnine.testing.DefaultFilter.DEFAULT);
        List<Filter> customFilters = new ArrayList<>();
        customFilters.add((Flight flight) -> flight.getSegments().stream().noneMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now(ZoneOffset.UTC))));
        customFilters.add((Flight flight) -> flight.getSegments().stream().noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())));
        customFilters.add((Flight flight) -> {
            Iterator<Segment> iterator = flight.getSegments().iterator();
            Segment current;
            Segment previous = null;
            while (iterator.hasNext()) {
                current = iterator.next();
                if (previous != null && previous.getArrivalDate().plusHours(2).isBefore(current.getDepartureDate())) {
                    return false;
                }
                previous = current;
            }
            return true;
        });

        System.out.println(Avia.filter(FlightBuilder.createFlights(), Collections.emptyList(), customFilters.get(0)));
        System.out.println(Avia.filter(FlightBuilder.createFlights(), Collections.emptyList(), customFilters.get(1)));
        System.out.println(Avia.filter(FlightBuilder.createFlights(), Collections.emptyList(), customFilters.get(2)));
    }


}





