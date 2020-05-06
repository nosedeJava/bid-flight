package project.app.persistence;

import org.springframework.data.repository.CrudRepository;

import project.app.model.Bid;

/**
 * BidRepository
 */
public interface BidRepository extends CrudRepository<Bid, Integer>{

}