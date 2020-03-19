package project.app.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.app.model.Flight;
import project.app.services.AuctionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * BidFlightController
 */
@RestController
public class BidFlightController {

    @Autowired
    private AuctionServices auctionServices;

    @RequestMapping(value = "/auctions", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctions() {
        /**
         * TO DO
         */
        return new ResponseEntity<>(auctionServices.getAllAuctions(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/auctions", method = RequestMethod.POST)
    public ResponseEntity<?> addFlight(@RequestBody Flight flight) {
        /**
         * TO DO
         */
        return null;
    }

    
    
}