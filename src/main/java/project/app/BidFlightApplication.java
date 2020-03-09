package project.app;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import project.app.model.User;
import project.app.model.Payment;
import project.app.persistence.UserRepository;

@SpringBootApplication
public class BidFlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidFlightApplication.class, args);
	}



}
