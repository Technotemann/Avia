import java.time.LocalDateTime;
import java.time.ZoneOffset;



public enum DefaultFilter {
    DEFAULT((Flight flight) -> flight.getSegments().stream().allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now(ZoneOffset.UTC))));

    //DEFAULT3(DefaultFilter::filterStatic);

    public Filter filter;

    DefaultFilter(Filter filter) {
        this.filter = filter;
    }

//    public static boolean filterStatic(Flight flight) {
//        return flight.getSegments().get(0).getArrivalDate().isAfter(LocalDateTime.now(ZoneOffset.UTC));
//    }
}