package com.udea.vuelo.utils;

public class FlightValidation {


    /*
    This method checks if destination and origin come in the request with value
    */
    public static boolean isOriginAndDestinationValid(String origin, String destination){

        return !origin.isEmpty() && !destination.isEmpty();
    }

    /*
    This method checks if destination comes in the request with value
    **/
    public static boolean isDestinationValid(String destination){

        return !destination.isEmpty();
    }

    /*
    This method checks if origin comes in the request with value
    * */
    public static boolean isOriginValid(String origin){

        return !origin.isEmpty();
    }
}
