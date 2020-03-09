package project.app.persistence;

import org.springframework.data.repository.CrudRepository;

import project.app.model.Flight;

/**
 * FlightRepository
 */
public interface FlightRepository extends CrudRepository<Flight, Integer>{
    
    
}