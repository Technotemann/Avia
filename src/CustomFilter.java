import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Iterator;

public enum CustomFilter {

    CUSTOM1((Flight flight) -> flight.getSegments().stream().noneMatch(segment -> segment.getDepartureDate().isBefore(LocalDateTime.now(ZoneOffset.UTC)))),
    CUSTOM2((Flight flight) -> flight.getSegments().stream().noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()))),
    CUSTOM3((Flight flight) -> {
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
    public Filter filter;

    CustomFilter(Filter filter) {
        this.filter = filter;
    }
}