package project.app.persistence;

import org.springframework.data.repository.CrudRepository;

import project.app.model.Ticket;

/**
 * TicketRepository
 */
public interface TicketRepository extends CrudRepository<Ticket, Integer>{

    
}