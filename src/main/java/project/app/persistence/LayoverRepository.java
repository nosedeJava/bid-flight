package project.app.persistence;

import org.springframework.data.repository.CrudRepository;

import project.app.model.Layover;

/**
 * LayoverRepository
 */
public interface LayoverRepository extends CrudRepository<Layover, Integer>{

    
}