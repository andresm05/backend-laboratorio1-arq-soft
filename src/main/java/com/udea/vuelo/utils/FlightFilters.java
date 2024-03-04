package com.udea.vuelo.utils;

import com.udea.vuelo.model.Flight;

import java.time.LocalDate;

public class FlightFilters {

    public static boolean isDateInRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate){
        //verifica si la fecha esta en el rango correcto
        return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
    }

    public static boolean isOriginAndDestination(Flight flight, String origin, String destination){
        return flight.getDestination().equalsIgnoreCase(destination) &&
                flight.getOrigin().equalsIgnoreCase(origin);
    }

    public static boolean isAirline(String airlineToCheck, String airline){
        return  airlineToCheck.equalsIgnoreCase(airline);
    }

    public static boolean isPriceSuitable(double priceToCheck, double minimum, double limit){
        return priceToCheck >= minimum && priceToCheck <=limit;
    }
}
