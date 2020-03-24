package project.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.app.model.Flight;
import project.app.services.AuctionServices;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
    @Autowired
    AuctionServices auctionServices;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctions() {
        try{
            return new ResponseEntity<>(auctionServices.getAllAuctions(), HttpStatus.ACCEPTED);
        }catch(Exception ex){
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar la subasta", HttpStatus.NOT_FOUND);
        }
    }

    //por reparar
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addFlight(@RequestBody Flight flight){
        try{
            auctionServices.addAuction(flight);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch(Exception ex){
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error en la creacion", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctionbById(@PathVariable("id") int id) {
        try{
            return new ResponseEntity<>(auctionServices.getAuctionById(id), HttpStatus.ACCEPTED);
        }catch(Exception ex){
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar subasta", HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> DeleteAuctions(@PathVariable("id") int id) {
        try{
            auctionServices.deleteAuctionByid(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }catch(Exception ex){
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al localizar subasta", HttpStatus.NOT_FOUND);
        }
    }




}
