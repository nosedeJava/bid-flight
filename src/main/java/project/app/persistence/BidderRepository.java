package project.app.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import project.app.model.Bidder;

/**
 * BidderRepository
 */
public interface BidderRepository extends CrudRepository<Bidder, String>{

    public Bidder findByMail(String mail);

    public Bidder findByUsername(String username);

    @Query(value ="update bidder set balance= :newbalance where username = :user",nativeQuery = true)
    public void updateBalance(@Param("newbalance") Float newbalance,@Param("user") String user);
}