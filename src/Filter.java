@FunctionalInterface
public interface Filter {
    boolean isValid(Flight flight);
}