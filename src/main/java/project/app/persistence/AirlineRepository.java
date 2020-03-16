package project.app.persistence;

import org.springframework.data.repository.CrudRepository;

import project.app.model.Airline;

/**
 * AirlineRepository
 */
public interface AirlineRepository extends CrudRepository<Airline, String>{

    public Airline findByName(String name);

    
}