package project.app.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.app.exception.AuctionNotFound;
import project.app.model.Auction;
import project.app.model.Bid;
import project.app.model.Bidder;
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
    public Set<Auction> getAllAuctions(Map<String, String> filters) {
        Set<Auction> auctions = new HashSet<>();
        auctions.addAll((Collection<? extends Auction>) auctionRepository.findAll());
        // Para evitarnos recurrencias infinitas a la hora de mandar la información como string, debemos limpiar algunos datos.
        for (Auction auction : auctions) {
            auction.getTicket().getFlight().setTickets(null);
            for(Bid bid:auction.getBids()){
                bid.setAuction(null);
            }
        }
        
        return auctions;
    }
    
    /**
     * 
     * @param auctions
     * @param filters
     * @return
     */
    private Set<Auction> completeFiltering(Set<Auction> auctions, Map<String, String> filters){
        List<Auction> newAuctions = new ArrayList<>();
        for (Auction auction : auctions) {
            boolean valid = true;
            for (String filter : filters.keySet()) {
                if(filter.equals("source")){
                    valid = valid&&auction.getTicket().getFlight().getSource().contains(filters.get(filter));
                    continue;
                }
                if(filter.equals("destiny")){
                    valid = valid&&auction.getTicket().getFlight().getDestiny().contains(filters.get(filter));
                    continue;
                }
                if(filter.equals("flighttype")){
                    valid = valid&&auction.getTicket().getType().equals(filters.get(filter));
                    continue;
                }
                if(filter.equals("bagtype")){
                    valid = valid&&auction.getTicket().getBagtype().equals(filters.get(filter));
                    continue;
                }
            }
            if(valid){
                newAuctions.add(auction);
            }
        }
        // Ahora ordenamos
        if(filters.containsKey("orderby")){
            order(filters.get("orderby"), newAuctions);
        }

        return null;
    }


    private List<Auction> order(String param, List<Auction> auctions){
        if(param.equals("price")){
            
        }
        return null;
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
        for (Bid bid : auction.getBids()) {
            Bidder bidder = bid.getBidder();
            bidder.setBalance(0);   bidder.setBids(null);   bidder.setDocument(null);   bidder.setDocumenttype(null);   bidder.setLastnames(null);  bidder.setNames(null);  bidder.setPassword(null);   bidder.setPayments(null);
        }
        return auction;
    }

    /**
     * Elimina una subasta basandose en una busqueda por ID
     * @param id
     * @throws AuctionNotFound
     */
    public void deleteAuctionByid(int id) throws AuctionNotFound{

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

    /**
     * 
     * @param auctions
     * @return
     
    private Set<Auction> filterByDate(Set<Auction> auctions){
        Date today = new Date(System.currentTimeMillis());
        for (Auction auction : auctions) {
            Date auctionDate = auction.getDueDate();
            if(today.compareTo(auctionDate)>=0){
                auctions.remove(auction);
            }
        }
        return auctions;
    }*/
}