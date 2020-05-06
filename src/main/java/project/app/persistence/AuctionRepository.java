package project.app.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import project.app.model.Auction;

/**
 * AuctionRepository
 */
public interface AuctionRepository extends CrudRepository<Auction, Integer> {

    public Auction findById(int id);

}