package project.app.persistence;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import project.app.model.Ticket;

/**
 * TicketRepository
 */
public interface TicketRepository extends CrudRepository<Ticket, Integer>{
    
    @Query(value ="update ticket set price= :newprice where id = :ticketid",nativeQuery = true)
    public void updatePrice(@Param("newprice") Float newprice,@Param("ticketid") Integer ticketid);

    
}