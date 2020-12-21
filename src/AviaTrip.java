import com.sun.istack.internal.NotNull;
import org.omg.CORBA.CustomMarshal;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class AviaTrip {

    public static void main(String[] args) {
//        List<DefaultFilter> defaultFilters = new ArrayList<>();
//        defaultFilters.add(DefaultFilter.DEFAULT);
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
        System.out.println(filter(FlightBuilder.createFlights(), Collections.emptyList(),  customFilters.get(0)));
        System.out.println(filter(FlightBuilder.createFlights(), Collections.emptyList(),  customFilters.get(1));
        System.out.println(filter(FlightBuilder.createFlights(), Collections.emptyList(), customFilters.get(2)));
    }

    public static List<Flight> filter(@NotNull List<Flight> flights, @NotNull List<DefaultFilter> defaultFilters, @NotNull List<Filter> customFilters) {
        return flights.stream().filter(flight -> {
            for (DefaultFilter defaultFilter : defaultFilters) {
                if (!defaultFilter.filter.isValid(flight)) {
                    return false;
                }
            }
            return true;
        }).filter(flight -> {
            for (Filter customFilter : customFilters) {
                if (!customFilter.isValid(flight)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }
}





