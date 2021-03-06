package project.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.app.model.Flight;
import project.app.services.AuctionServices;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
    @Autowired
    private AuctionServices auctionServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctions(@RequestParam Map<String, String> parameters) {
        try {
            return new ResponseEntity<>(auctionServices.getAllAuctions(parameters), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar la subasta", HttpStatus.NOT_FOUND);
        }
    }

    // por reparar
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addFlight(@RequestBody Flight flight) {
        try {
            auctionServices.addAuction(flight);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error en la creacion", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctionbById(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(auctionServices.getAuctionById(id), HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar subasta", HttpStatus.NOT_FOUND);
        }
    }

}
