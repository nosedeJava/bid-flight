package project.app.persistence;

import org.springframework.data.repository.CrudRepository;

import project.app.model.Auction;

/**
 * AuctionRepository
 */
public interface AuctionRepository extends CrudRepository<Auction, Integer> {

    public Auction findById(int id);

}