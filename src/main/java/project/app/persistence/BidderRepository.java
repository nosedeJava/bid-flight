package project.app.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.app.model.Bidder;

/**
 * BidderRepository
 */
@Repository
public interface BidderRepository extends CrudRepository<Bidder, String>{

    
}