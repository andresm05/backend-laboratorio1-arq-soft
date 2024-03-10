package com.udea.vuelo.utils;

public class FlightValidation {

    public static boolean isOriginAndDestinationValid(String origin, String destination){

        return !origin.isEmpty() && !destination.isEmpty();
    }

    public static boolean isDestinationValid(String destination){

        return !destination.isEmpty();
    }

    public static boolean isOriginValid(String origin){

        return !origin.isEmpty();
    }
}
