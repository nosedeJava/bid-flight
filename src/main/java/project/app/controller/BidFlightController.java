package project.app.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

//import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import project.app.model.Airline;
import project.app.model.Bidder;
import project.app.model.Flight;
import project.app.model.Role;
import project.app.services.AirlineServices;
import project.app.services.AuctionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import project.app.services.BidderServices;

/**
 * BidFlightController
 */
@RestController
public class BidFlightController {

    @Autowired
    private AuctionServices auctionServices;

    @Autowired
    private BidderServices bidderServices;

    @Autowired
    private AirlineServices airlineServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/auctions", method = RequestMethod.GET)
    //@GetMapping("/auctions")
    //@ApiOperation(value = "Get all auctions",notes = "return all auctions")
    public ResponseEntity<?> getAuctions() {
        try{
            return new ResponseEntity<>(auctionServices.getAllAuctions(), HttpStatus.ACCEPTED);
        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar la lista de Blueprints", HttpStatus.NOT_FOUND);
        }
    }


    //por reparar
    @RequestMapping(value = "/auctions", method = RequestMethod.POST)
    public ResponseEntity<?> addFlight(@RequestBody Flight flight){
        try{
            auctionServices.addAuction(flight);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error en la creacion", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/auctions/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctionbById(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(auctionServices.getAuctionById(id), HttpStatus.ACCEPTED);
        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar subasta", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/auctions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteAuctions(@PathVariable("id") int id) {
        try{
            auctionServices.deleteAuctionByid(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar subasta", HttpStatus.NOT_FOUND);
        }
    }


    //accounts


    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public ResponseEntity<?> addAccount(@RequestBody Bidder bidder){
        try{
            bidder.setRole(Role.BIDDER);
            bidder.setPassword(passwordEncoder.encode(bidder.getPassword()));
            bidderServices.addAccount(bidder);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error en la creacion de la cuenta", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/accounts/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(@PathVariable("username") String username){
        try{
            return new ResponseEntity<>(bidderServices.getBidder(username),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar la cuenta", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/accounts/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> putAccount(@RequestBody Bidder bidder,@PathVariable("username") String username){
        try{
            bidderServices.updateBidder(bidder,username);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al modificar la cuenta", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/accounts/{username}/auctions", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctionByBidder(@PathVariable("username") String username){
        try{
            return new ResponseEntity<>(bidderServices.getAuctionsPerUser(username),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar la cuenta", HttpStatus.FORBIDDEN);
        }

    }

    //Airlines

    @RequestMapping(value = "/airlines/{airline}", method = RequestMethod.GET)
    public ResponseEntity<?> getAirline(@PathVariable("airline") String airline){
        try{
            return new ResponseEntity<>(airlineServices.getAirlineByName(airline),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar la aerolinea", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}", method = RequestMethod.PUT)
    public ResponseEntity<?> putAirline(@RequestBody Airline airline,@PathVariable("name") String name){
        try{
            airlineServices.updateAirline(name,airline);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al modificar la aerolinea", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights", method = RequestMethod.GET)
    public ResponseEntity<?> getFlightByAirline(@PathVariable("airline") String airline){
        try{
            return new ResponseEntity<>(airlineServices.getAirlineFlights(airline),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar los vuelos de esta aerolinea", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights", method = RequestMethod.POST)
    public ResponseEntity<?> postFlightByAirline(@RequestBody Flight flight,@PathVariable("airline") String airline){
        try{
            System.out.println(flight.toString());
            airlineServices.addFlight(airline,flight);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al agregar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getFlightById(@PathVariable("airline") String airline,@PathVariable("id") int id){
        try{
            return new ResponseEntity<>(airlineServices.getAirlineFlightById(airline,id),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights", method = RequestMethod.PUT)
    public ResponseEntity<?> postFlightById(@RequestBody Flight flight,@PathVariable("airline") String airline,@PathVariable("id") int idFlight){
        try{
            airlineServices.updateFlight(airline,idFlight,flight);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al actualizar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines/{airline}/flights/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> RemoveFlightById(@PathVariable("airline") String airline, @PathVariable("id") int idFlight){
        try{
            airlineServices.removeAirlineFlight(airline,idFlight);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al eliminar el vuelo", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/airlines", method = RequestMethod.POST)
    public ResponseEntity<?> postAirline(@RequestBody Airline airline){
        try{
            airlineServices.addAirline(airline);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(BidFlightController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al agregar la aerolinea", HttpStatus.FORBIDDEN);
        }

    }






}