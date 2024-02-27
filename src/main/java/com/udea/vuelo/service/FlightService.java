package com.udea.vuelo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.udea.vuelo.model.Flight;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {
    //Ruta del archivo
    private final String filePath="classpath:flights.json";

    //Metodo de la logica de busqueda de vuelos
    public List<List<Flight>> searchFligths(LocalDate startDate,LocalDate endDate){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("flights.json");

            if(inputStream != null){
                Flight[] flights = objectMapper.readValue(inputStream, Flight[].class);
                return Arrays.asList(
                        Arrays.stream(flights)
                                .filter(flight -> isDateInRange(flight.getDepartureDate(), startDate,endDate))
                                .collect(Collectors.toList()));
            } else {
                return null;
            }

        }catch (IOException e){
            throw new RuntimeException("Error leyendo el archivo JSON ", e);
        }
    }
    private boolean isDateInRange(LocalDate dateToCheck, LocalDate startDate, LocalDate endDate){
        //verifica si la fecha esta en el rango correcto
        return !dateToCheck.isBefore(startDate) && !dateToCheck.isAfter(endDate);
    }


}