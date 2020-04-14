package com.apap.tu07.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apap.tu07.model.FlightModel;
import com.apap.tu07.model.PilotModel;
import com.apap.tu07.service.FlightService;
import com.apap.tu07.service.PilotService;

/**
 * FlightController
 */
@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private PilotService pilotService;
    
    @PostMapping(value="/add/{licenseNumber}")
	public FlightModel addFlight(@PathVariable(value="licenseNumber")String licenseNumber, @RequestBody FlightModel flight, Model model) {
    	PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
    	flight.setPilot(pilot);
    	return flightService.addFlight(flight);
	}
	
	@PutMapping(value="/update/{flightId}")
	private String updatePilot(@PathVariable(value="flightId") long id,@RequestParam("destination") String destination, @RequestParam("origin") String origin,@RequestParam("time") Date time) {
		FlightModel flight = flightService.flightById(id);
		if (flight.equals(null)) {
			return "Couldn't find your flight";
		}
		flight.setOrigin(origin);
		flight.setDestination(destination);
		flight.setTime(time);
		flightService.updateFlight(flight);
		return "flight update success";
	}
	
	@DeleteMapping(value="/{flightId}")
	public String deletePilot(@PathVariable(value="flightId") long id) {
		flightService.removeFlight(id);
		return "flight has been deleted";
	}
	
	@GetMapping(value="/view/{flightNumber}")
	public FlightModel getFlight(@PathVariable("flightNumber") String flightNumber) {
		FlightModel flight = flightService.findByFlightNumber(flightNumber).get();
		return flight;
	}
	
	@GetMapping(value="/all")
	public List<FlightModel> getAllFlight() {
		List<FlightModel> flight = flightService.getAllFlight();
		return flight;
	}
	
}