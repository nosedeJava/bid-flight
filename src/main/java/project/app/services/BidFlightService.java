package project.app.services;

import org.springframework.stereotype.Service;

import project.app.model.Flight;

/**
 * BidFlightService
 */
@Service
public interface BidFlightService {

    public void addNewFlight(Flight flight);

    public List<Auction> getAuctions();
    
}