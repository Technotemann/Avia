package com.gridnine.testing;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class Avia {
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
    public static List<Flight> filter(@NotNull List<Flight> flights, @NotNull List<DefaultFilter> defaultFilters, @NotNull Filter customFilter) {
        return flights.stream().filter(flight -> {
            for (DefaultFilter defaultFilter : defaultFilters) {
                if (!defaultFilter.filter.isValid(flight)) {
                    return false;
                }
            }
            return true;
        }).filter(flight -> {
                if (!customFilter.isValid(flight)) {
                    return false;
                }
            return true;
        }).collect(Collectors.toList());
    }

}
