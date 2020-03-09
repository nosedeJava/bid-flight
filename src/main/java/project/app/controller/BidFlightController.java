package project.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.app.model.Payment;
import project.app.model.User;
import project.app.persistence.UserRepository;



import java.util.HashSet;
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

    @RequestMapping(value = "/auctions", method = RequestMethod.GET)
    public ResponseEntity<?> getAuctions() {
        System.out.println("Entre prro jijijijijijijiji");
        Payment payment = new Payment(null, "Tarjeta de credito", "Información que debería ser util jaja salu2");
		Set<Payment> payments = new HashSet<>();
		payments.add(payment);
		User user = new User("Stilink306@gmail.com","password", "Nombres 1 y 2", "apellidos 1 y 2", "stilink", "CC", "123456789", (float)5000000, payments);
		payment.setUser(user);
		repository.save(user);
        System.out.println(repository.findAll().toString());
        return new ResponseEntity<>(repository.findAll().toString(),HttpStatus.ACCEPTED);
    }

    
    
}