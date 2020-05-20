package project.app.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import project.app.model.Airline;
import project.app.model.Flight;
import project.app.services.AirlineServices;
import project.app.services.AuctionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * BidFlightController
 */
@RestController
public class AirlineController {

    @Autowired
    private AirlineServices airlineServices;

    @Autowired
    private AuctionServices auctionServices;



    @RequestMapping(value = "/airlines/{airline}", method = RequestMethod.GET)
    public ResponseEntity<?> getAirline(@PathVariable("airline") String airline){
        try{
            return new ResponseEntity<>(airlineServices.getAirlineByName(airline),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar la aerolinea", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}", method = RequestMethod.PUT)
    public ResponseEntity<?> putAirline(@RequestBody Airline airline,@PathVariable("name") String name){
        try{
            airlineServices.updateAirline(name,airline);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al modificar la aerolinea", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights", method = RequestMethod.GET)
    public ResponseEntity<?> getFlightByAirline(@PathVariable("airline") String airline){
        try{
            return new ResponseEntity<>(airlineServices.getAirlineFlights(airline),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar los vuelos de esta aerolinea", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights", method = RequestMethod.POST)
    public ResponseEntity<?> postFlightByAirline(@RequestBody Flight flight,@PathVariable("airline") String airline){
        try{
            airlineServices.addFlight(airline,flight);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al agregar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFlightById(@PathVariable("airline") String airline,@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(airlineServices.getAirlineFlightById(airline,id),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeFlightById(@PathVariable("airline") String airline, @PathVariable("id") int idFlight){
        try{
            airlineServices.removeAirlineFlight(airline,idFlight);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al eliminar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines", method = RequestMethod.POST)
    public ResponseEntity<?> postAirline(@RequestBody Airline airline){
        try{
            airlineServices.addAirline(airline);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al agregar la aerolinea", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/auctions", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctions(@PathVariable("airline") String airline){
        try{
            return new ResponseEntity<>(auctionServices.getAllAuctionByAirline(airline),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/flights/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFlightById(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(airlineServices.getFlightById(id),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar el vuelo", HttpStatus.FORBIDDEN);
        }

    }    

}