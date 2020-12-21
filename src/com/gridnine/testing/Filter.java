package com.gridnine.testing;

import java.util.List;

@FunctionalInterface
public interface Filter{
    boolean isValid(Flight flight);
}