package project.app.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import project.app.model.User;

/**
 * UserRepository
 */
@Repository
public interface UserRepository extends CrudRepository<User, String>{

    
}