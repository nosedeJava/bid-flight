package project.app.services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.app.exception.AuctionNotFound;
import project.app.model.Auction;
import project.app.model.Flight;
import project.app.model.Ticket;
import project.app.persistence.AuctionRepository;
import project.app.persistence.FlightRepository;

/**
 * AuctionServices
 */
@Service
public class AuctionServices {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private FlightRepository flightRepository;

    /**
     * Operación realizada para obtener todas las subastas.
     * 
     * @return Set con todas las subastas
     */
    public Set<Auction> getAllAuctions() {
        Set<Auction> auctions = new HashSet<>();
        auctions.addAll((Collection<? extends Auction>) auctionRepository.findAll());
        // Para evitarnos recurrencias infinitas a la hora de mandar la información como string, debemos limpiar algunos datos.
        for (Auction auction : auctions) {
            auction.getTicket().getFlight().setTickets(null);
        }
        return auctions;
    }
    

    /**
     * Recibe un vuelo que contiene una serie de tickets en busca de crear sus subastas
     * @param flight Vuelo que contiene una serie de tickets dentro.
     */
    public void addAuction(Flight flight){
        // Salvamos el vuelo primero
        flightRepository.save(flight);
        Date dueDate = calculateDate(flight);
        Set<Ticket> tickets = flight.getTickets();
        for (Ticket ticket : tickets) {
            Auction auction = new Auction(ticket, dueDate);
            auctionRepository.save(auction);
        }
    }

    /**
     * Obtiene una subasta basandose en una busqueda por ID
     * @param id Identificador de una subasta
     * @return Subasta asociada al identificador indicado
     * @throws AuctionNotFound En caso de no encontrar la subasta se lanzará esta excepción
     */
    public Auction getAuctionById(int id) throws AuctionNotFound{
        Auction auction = null;
        try{
            auction = auctionRepository.findById(id);
            if(auction==null){
                throw new AuctionNotFound("Subasta no encontrada");
            }
        }catch(Exception e){
            throw new AuctionNotFound("Subasta no encontrada");
        }
        auction.getTicket().getFlight().setTickets(null);
        return auction;
    }



    /**
     * TO DO - Remove
     */

    /**
     * TO DO - Put
     */



    /**
     * Metodo interno para el calculo de las subastas, originalmente quemado a 10h antes de la salida del vuelo.
     * @param flight Vuelo al que se desea crearle sus subastas.
     * @return Fecha final seleccionada para las subastas de los tiquetes.
     */
    private Date calculateDate(Flight flight){
        Date dueDate = null;
        Date flightDate = flight.getTakeoffdate(); 
        Calendar cl = Calendar.getInstance();
        cl.setTime(flightDate);
        cl.add(Calendar.HOUR, -10);
        dueDate = cl.getTime();
        return dueDate;
    }

}