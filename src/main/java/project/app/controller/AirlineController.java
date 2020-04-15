package project.app.controller;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import project.app.model.Airline;
import project.app.model.Flight;
import project.app.services.AirlineServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * BidFlightController
 */
@RestController
public class AirlineController {

    @Autowired
    private AirlineServices airlineServices;



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

    @RequestMapping(value = "/airlines/{airline}/flights", method = RequestMethod.PUT)
    public ResponseEntity<?> postFlightByIdAndAirline(@RequestBody Flight flight,@PathVariable("airline") String airline,@PathVariable("id") int idFlight){
        try{
            airlineServices.updateFlight(airline,idFlight,flight);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al actualizar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> RemoveFlightById(@PathVariable("airline") String airline, @PathVariable("id") int idFlight){
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

    @RequestMapping(value = "/flights/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFlightById(@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(airlineServices.getFlightById(id),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar el vuelo", HttpStatus.FORBIDDEN);
        }

    }



    @RequestMapping(value = "/flights/ticket", method = RequestMethod.GET)
    public ResponseEntity<?> TicketFilter(@RequestParam(value = "date", required = false) Date date,
                                            @RequestParam(value = "airline", required = false) String AirlineName,
                                            @RequestParam(value = "source", required = false) String source,
                                            @RequestParam(value = "destiny",required = false) String destiny,
                                            @RequestParam(value = "price",required = false) float price,
                                            @RequestParam(value = "type",required = false) String type,
                                            @RequestParam(value = "sort",required = false) String sort

    ){
        try{
            return null;
            //return new ResponseEntity<>(airlineServices.getAirlineFlightById(id),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AirlineController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    

}