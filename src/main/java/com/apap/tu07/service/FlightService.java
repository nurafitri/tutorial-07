package com.apap.tu07.service;

import java.util.List;
import java.util.Optional;

import com.apap.tu07.model.FlightModel;

/**
 * FlightService
 */
public interface FlightService {
    
    FlightModel addFlight(FlightModel flight);
	
    void updateFlight(FlightModel flightModel);
	
    FlightModel flightById(long id);
    
    Optional<FlightModel> findByFlightNumber(String flightNumber);
	
    void removeFlight(long id);
	
    List <FlightModel> getAllFlight();
}