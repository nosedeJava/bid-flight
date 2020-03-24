package project.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import project.app.model.Bidder;
import project.app.model.Role;
import project.app.services.BidderServices;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/accounts")
public class BidderController {

    @Autowired
    private BidderServices bidderServices;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> addAccount(@RequestBody Bidder bidder){
        try{
            bidder.setPassword(passwordEncoder.encode(bidder.getPassword()));
            bidder.setRole(Role.BIDDER);
            bidderServices.addAccount(bidder);
            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch(Exception ex){
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error en la creacion de la cuenta", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount(@PathVariable("username") String username){
        try{
            return new ResponseEntity<>(bidderServices.getBidder(username),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar la cuenta", HttpStatus.FORBIDDEN);
        }

    }

    @RequestMapping(value = "/{username}", method = RequestMethod.PUT)
    public ResponseEntity<?> putAccount(@RequestBody Bidder bidder,@PathVariable("username") String username){
        try{
            bidderServices.updateBidder(bidder,username);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al modificar la cuenta", HttpStatus.FORBIDDEN);
        }

    }
    @RequestMapping(value = "/{username}/auctions", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctionByBidder(@PathVariable("username") String username){
        try{
            return new ResponseEntity<>(bidderServices.getAuctionsPerUser(username),HttpStatus.ACCEPTED);

        }catch(Exception ex){
            Logger.getLogger(AuctionController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error al encontrar la cuenta", HttpStatus.FORBIDDEN);
        }

    }


    /*@RequestMapping(value = "/sessionID", method = RequestMethod.GET)
    public ResponseEntity<?> getSessionID(HttpServletRequest request){
        //Bidder bidder = (Bidder) request.getSession().getAttribute("Bidder");
        System.out.println(request.getSession().getAttribute("username"));
        return new ResponseEntity<>("Prueba",HttpStatus.ACCEPTED);
    }*/

}
