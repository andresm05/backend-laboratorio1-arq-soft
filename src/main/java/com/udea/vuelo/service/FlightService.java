package com.udea.vuelo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.vuelo.exceptions.RestException;
import com.udea.vuelo.exceptions.RouteNotValidException;
import com.udea.vuelo.model.Flight;
import com.udea.vuelo.utils.FlightFilters;
import com.udea.vuelo.utils.FlightValidation;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class FlightService {

    //This method is used to read the file that contains the data and filters the result
    //according to a specific predicate
    private List<Flight> searchFlights(Predicate<Flight> filter){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("flights.json");

            if (inputStream != null) {
                Flight[] flights = objectMapper.readValue(inputStream, Flight[].class);
                return
                        Arrays.stream(flights)
                                .filter(filter)
                                .collect(Collectors.toList());
            } else {
                return null;
            }

        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo JSON ", e);
        }
    }

    //This method is used for searching flights that are in a particular date range
    public List<Flight> searchFlightsByDate(LocalDate startDate, LocalDate endDate) {
        return searchFlights(flight -> FlightFilters.isDateInRange(flight.getDepartureDate(), startDate, endDate));
    }

    //This method is used for searching flights that fulfill with a specific origin and destination
    public List<Flight> searchFlightsByRoute(String origin, String destination) throws RestException {
        if(FlightValidation.isOriginAndDestinationValid(origin, destination)){
            return searchFlights(flight -> FlightFilters.isOriginAndDestination(flight, origin, destination));

        }
        if(FlightValidation.isDestinationValid(destination)){
            return searchFlights(flight -> FlightFilters.isDestination(flight.getDestination(), destination));
        }

        if(FlightValidation.isOriginValid(origin)){
            return searchFlights(flight -> FlightFilters.isOrigin(flight.getOrigin(), origin));
        }
        /*Throw an exception when neither destination nor origin come in the request*/
        throw new RouteNotValidException("Debe proporcionar al menos un valor para origen o destino");
    }

    //This method is used for searching flights that fulfill with a particular airline
    public List<Flight> searchFlightByAirline(String airline){
        return searchFlights(flight -> FlightFilters.isAirline(flight.getAirline(), airline));
    }

    public List<Flight> searchFlightByPriceRange(double minimum, double limit){
        return searchFlights(flight -> FlightFilters.isPriceSuitable(flight.getPrice(), minimum, limit));
    }


}