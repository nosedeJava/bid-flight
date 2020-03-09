package project.app.services;

import org.springframework.stereotype.Service;

import java.util.List;

import project.app.model.Auction;
import project.app.model.Flight;

/**
 * BidFlightService
 */
@Service
public interface BidFlightService {
    // Metodo pensado para el post de las aerolineas con sus tiquetes
    public void addNewFlight(Flight flight);
    // Metodo pensado para la busqueda
    public List<Auction> getAuctions();
    
}