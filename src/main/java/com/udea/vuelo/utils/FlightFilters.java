package com.udea.vuelo.utils;

import com.udea.vuelo.model.Flight;

import java.time.LocalDate;

public class FlightFilters {

    //This method is to check if a flight fits between a date range
    public static boolean isDateInRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate){
        //check if date is in the proper range
        return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
    }

    //This method is to check if a flight fits for a specific origin and destination
    public static boolean isOriginAndDestination(Flight flight, String origin, String destination){
        return flight.getDestination().equalsIgnoreCase(destination) &&
                flight.getOrigin().equalsIgnoreCase(origin);
    }

    //This method check if a flight fits for a specific airline
    public static boolean isAirline(String airlineToCheck, String airline){
        return  airlineToCheck.equalsIgnoreCase(airline);
    }

    //This method check if flight price is between a specific range
    public static boolean isPriceSuitable(double priceToCheck, double minimum, double limit){
        return priceToCheck >= minimum && priceToCheck <=limit;
    }
}
