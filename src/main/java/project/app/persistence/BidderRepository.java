package project.app.persistence;

import org.springframework.data.repository.CrudRepository;

import project.app.model.Bidder;

/**
 * BidderRepository
 */
public interface BidderRepository extends CrudRepository<Bidder, String>{

    public Bidder findByMail(String mail);

    public Bidder findByUsername(String username);
}