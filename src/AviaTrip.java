import com.sun.istack.internal.NotNull;
import org.omg.CORBA.CustomMarshal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AviaTrip {

    public static void main(String[] args) {
        List<DefaultFilter> defaultFilters = new ArrayList<>();
        defaultFilters.add(DefaultFilter.DEFAULT);
        List<CustomFilter> customFilters = new ArrayList<>();
        customFilters.add(CustomFilter.CUSTOM1);
        customFilters.add(CustomFilter.CUSTOM2);
        customFilters.add(CustomFilter.CUSTOM3);
        System.out.println(filter(FlightBuilder.createFlights(), defaultFilters,  customFilters.get(0)));
        System.out.println(filter(FlightBuilder.createFlights(), defaultFilters,  customFilters.get(1));
        System.out.println(filter(FlightBuilder.createFlights(), defaultFilters, customFilters.get(2)));
    }

    public static List<Flight> filter(@NotNull List<Flight> flights, @NotNull List<DefaultFilter> defaultFilters, @NotNull List<CustomFilter> customFilters) {
        return flights.stream().filter(flight -> {
            for (DefaultFilter defaultFilter : defaultFilters) {
                if (!defaultFilter.filter.isValid(flight)) {
                    return false;
                }
            }
            return true;
        }).filter(flight -> {
            for (CustomFilter customFilter : customFilters) {
                if (!customFilter.filter.isValid(flight)) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }
}





