package project.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.app.model.Payment;
import project.app.model.User;
import project.app.persistence.UserRepository;
import project.app.persistence.PaymentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * BidFlightController
 */
@RestController
public class BidFlightController {
    @Autowired
    @Qualifier(value = "userRepository")
    private UserRepository repository;

    @Autowired
    private PaymentRepository paymentReposiroty;

    @RequestMapping(value = "/auctions", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctions() {
        /**
         * TO DO
         */
        return new ResponseEntity<>(repository.findAll().toString(),HttpStatus.ACCEPTED);
    }

    
    
}