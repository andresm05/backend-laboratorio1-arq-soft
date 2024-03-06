package com.udea.vuelo.controller;

import java.time.LocalDate;
import java.util.List;
import com.udea.vuelo.model.Flight;
import com.udea.vuelo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;

    //this is the path to search flights between a specific date
    @GetMapping("/search/date")
    public List<Flight> searchFligthsByDate(
            @RequestParam(name ="startDate") String startDate,
            @RequestParam(name="endDate") String endDate){
        LocalDate parsedStarDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate);
        return flightService.searchFlightsByDate(parsedStarDate,parsedEndDate);
    }

    //This is the path to search flights that fit with a specific origin and destination
    @GetMapping("/search/route")
    public List<Flight> searchFlightsByRoute(
            @RequestParam(name="origin") String origin,
            @RequestParam(name="destination") String destination
    ){
        return flightService.searchFlightsByRoute(origin, destination);
    }

    //This is the path to search flights that fit with a specific airline
    @GetMapping("/search/airline")
    public List<Flight> searchFlightsByAirline(
            @RequestParam(name="airline") String airline
    ){
        return flightService.searchFlightByAirline(airline);
    }

    //This is the path to search flights that are between a price range
    //minimum price is 0 by default
    @GetMapping("/search/price")
    public List<Flight> searchFlightsByPriceRange(
            @RequestParam(name="minimum", defaultValue = "0") Double minimum,
            @RequestParam(name="limit") Double limit
    ){
        return flightService.searchFlightByPriceRange(minimum, limit);
    }

}
