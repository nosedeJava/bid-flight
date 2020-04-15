package project.app.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
        // Para evitarnos recurrencias infinitas a la hora de mandar la información como
        // string, debemos limpiar algunos datos.
        for (Auction auction : auctions) {
            auction.getTicket().getFlight().setTickets(null);
            for (Bid bid : auction.getBids()) {
                bid.setAuction(null);
            }
        }
        completeFiltering(auctions, filters);
        return auctions;
    }

    /**
     * 
     * @param auctions
     * @param filters
     * @return
     */
    private Set<Auction> completeFiltering(Set<Auction> auctions, Map<String, String> filters) {
        List<Auction> newAuctions = new ArrayList<>();
        for (Auction auction : auctions) {
            boolean valid = true;
            if (filters.keySet().contains("source")) {
                System.out.println("Entre chavales - SOURCE");
                valid = valid && auction.getTicket().getFlight().getSource().contains(filters.get("source"));
            }
            if (filters.keySet().contains("destiny")) {
                System.out.println("Entre chavales - DESTINY 2 JAJA SALU2");
                System.out.println(valid);
                System.out.println(filters.get("destiny"));
                System.out.println(auction.getTicket().getFlight().getDestiny());
                System.out.println(auction.getTicket().getFlight().getDestiny().contains(filters.get("destiny")));
                valid = valid && auction.getTicket().getFlight().getDestiny().contains(filters.get("destiny"));
                System.out.println("-----------------------------------------------------------");
                System.out.println(valid);
            }
            if (filters.keySet().contains("flighttype")) {
                System.out.println("Entre chavales - TYPE");
                valid = valid && auction.getTicket().getType().equals(filters.get("flighttype"));
            }
            if (filters.keySet().contains("bagtype")) {
                System.out.println("Entre chavales - BAG");
                valid = valid && auction.getTicket().getBagtype().equals(filters.get("bagtype"));
            }
            if (valid) {
                newAuctions.add(auction);
            }
        }
        // Ahora ordenamos
        if (filters.containsKey("orderby")) {
            order(filters.get("orderby"), newAuctions);
        }
        Set<Auction> result = new HashSet<>();
        result.addAll(newAuctions);
        return result;
    }

    /**
     * 
     * @param param
     * @param auctions
     * @return
     */
    private List<Auction> order(String param, List<Auction> auctions) {
        if (param.equals("Price")) {
            Collections.sort(auctions, new Comparator<Auction>() {
                @Override
                public int compare(Auction o1, Auction o2) {
                    return Float.compare(o1.getTicket().getPrice(), o2.getTicket().getPrice());
                }
            });
        }
        else if(param.equals("Duration")){
            Collections.sort(auctions, new Comparator<Auction>() {
                @Override
                public int compare(Auction o1, Auction o2) {
                    return Integer.compare(o1.getTicket().getFlight().getDuration(), o2.getTicket().getFlight().getDuration());
                }
            });
        }
        else if(param.equals("Take off date")){
            /**
            * TO DO
            */
        }
        return auctions;
    }

    /**
     * Recibe un vuelo que contiene una serie de tickets en busca de crear sus
     * subastas
     * 
     * @param flight Vuelo que contiene una serie de tickets dentro.
     */
    public void addAuction(Flight flight) {
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
     * 
     * @param id Identificador de una subasta
     * @return Subasta asociada al identificador indicado
     * @throws AuctionNotFound En caso de no encontrar la subasta se lanzará esta
     *                         excepción
     */
    public Auction getAuctionById(int id) throws AuctionNotFound {
        Auction auction = null;
        try {
            auction = auctionRepository.findById(id);
            if (auction == null) {
                throw new AuctionNotFound("Subasta no encontrada");
            }
        } catch (Exception e) {
            throw new AuctionNotFound("Subasta no encontrada");
        }
        auction.getTicket().getFlight().setTickets(null);
        for (Bid bid : auction.getBids()) {
            Bidder bidder = bid.getBidder();
            bidder.setBalance(0);
            bidder.setBids(null);
            bidder.setDocument(null);
            bidder.setDocumenttype(null);
            bidder.setLastnames(null);
            bidder.setNames(null);
            bidder.setPassword(null);
            bidder.setPayments(null);
        }
        return auction;
    }

    /**
     * Elimina una subasta basandose en una busqueda por ID
     * 
     * @param id
     * @throws AuctionNotFound
     */
    public void deleteAuctionByid(int id) throws AuctionNotFound {

    }

    /**
     * TO DO - Remove
     */

    /**
     * TO DO - Put
     */

    /**
     * Metodo interno para el calculo de las subastas, originalmente quemado a 10h
     * antes de la salida del vuelo.
     * 
     * @param flight Vuelo al que se desea crearle sus subastas.
     * @return Fecha final seleccionada para las subastas de los tiquetes.
     */
    private Date calculateDate(Flight flight) {
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
     * 
     *         private Set<Auction> filterByDate(Set<Auction> auctions){ Date today
     *         = new Date(System.currentTimeMillis()); for (Auction auction :
     *         auctions) { Date auctionDate = auction.getDueDate();
     *         if(today.compareTo(auctionDate)>=0){ auctions.remove(auction); } }
     *         return auctions; }
     */
}