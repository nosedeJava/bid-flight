package project.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

/**
 * BidFlightController
 */
@RestController
public class BidFlightController {

    @RequestMapping(value = "/auctions", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctions() {
        /**
         * TO DO
         */
        return null;
    }

    
    
}