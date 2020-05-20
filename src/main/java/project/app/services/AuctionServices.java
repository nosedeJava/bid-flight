package project.app.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.app.exception.AuctionNotFound;
import project.app.exception.InconsistentBid;
import project.app.model.Auction;
import project.app.model.Bid;
import project.app.model.Bidder;
import project.app.model.Flight;
import project.app.model.Ticket;
import project.app.persistence.AuctionRepository;
import project.app.persistence.BidRepository;
import project.app.persistence.FlightRepository;
import project.app.persistence.TicketRepository;

/**
 * AuctionServices
 */
@Service
public class AuctionServices {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * Operación realizada para obtener todas las subastas.
     * 
     * @return List con todas las subastas
     */
    public List<Auction> getAllAuctions(Map<String, String> filters) {
        List<Auction> auctions = new ArrayList<>();
        auctions.addAll((Collection<? extends Auction>) auctionRepository.findAll());
        // Para evitarnos recurrencias infinitas a la hora de mandar la información como
        // string, debemos limpiar algunos datos.
        for (Auction auction : auctions) {
            auction.getTicket().getFlight().setTickets(null);
            auction.setBids(new HashSet<Bid>());
        }
        auctions = completeFiltering(auctions, filters);
        return auctions;
    }

    /**
     * 
     * @param auctions
     * @param filters
     * @return
     */
    private List<Auction> completeFiltering(List<Auction> auctions, Map<String, String> filters) {
        List<Auction> newAuctions = new ArrayList<>();
        for (Auction auction : auctions) {
            boolean valid = true;
            if (filters.keySet().contains("source")) {
                valid = valid && auction.getTicket().getFlight().getSource().contains(filters.get("source"));
            }
            if (filters.keySet().contains("destiny")) {
                valid = valid && auction.getTicket().getFlight().getDestiny().contains(filters.get("destiny"));
            }
            if (filters.keySet().contains("flighttype")) {
                valid = valid && auction.getTicket().getType().equals(filters.get("flighttype"));
            }
            if (filters.keySet().contains("bagtype")) {
                valid = valid && auction.getTicket().getBagtype().equals(filters.get("bagtype"));
            }
            if (valid) {
                newAuctions.add(auction);
            }
        }
        List<Auction> list = null;
        // Ahora ordenamos
        if (filters.containsKey("orderby")) {
            list = order(filters.get("orderby"), newAuctions);
        } else
            list = newAuctions;
        return list;
    }

    /**
     * 
     * @param param
     * @param auctions
     * @return
     */
    private List<Auction> order(String param, List<Auction> auctions) {
        // Orden por precio Menor - Mayor
        if (param.contains("Price")) {
            Collections.sort(auctions, new Comparator<Auction>() {
                @Override
                public int compare(Auction o1, Auction o2) {
                    return Float.compare(o1.getTicket().getPrice(), o2.getTicket().getPrice());
                }
            });
        }
        // Orden por duración Menor - Mayor
        else if (param.contains("Duration")) {
            Collections.sort(auctions, new Comparator<Auction>() {
                @Override
                public int compare(Auction o1, Auction o2) {
                    return Integer.compare(o1.getTicket().getFlight().getDuration(),
                            o2.getTicket().getFlight().getDuration());
                }
            });
        }
        // Orden por take off date Menor (Más cercana en el tiempo) - Mayor (Más lejana
        // en el tiempo)
        else if (param.contains("Take off date")) {
            Collections.sort(auctions, new Comparator<Auction>() {
                @Override
                public int compare(Auction o1, Auction o2) {
                    return o1.getDueDate().compareTo(o2.getDueDate());
                }
            });
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
        auction = auctionRepository.findById(id);
        if (auction == null) {
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
            bid.setAuction(null);
        }

        // Control para dar las bids organizadas
        Set<Bid> bids = new TreeSet<>(new Comparator<Bid>() {
			@Override
			public int compare(Bid b1, Bid b2) {
				return -Float.compare(b1.getAmount(), b2.getAmount());
			}
        });
        bids.addAll(auction.getBids());
        auction.setBids(bids);
        return auction;
    }

    /**
     * Actualiza una auction al recibir una nueva oferta.
     * 
     * @param bid
     * @throws AuctionNotFound
     * @throws InconsistentBid
     */
    public void updateAuction(Bid bid) throws AuctionNotFound, InconsistentBid {
        Auction auction = getAuctionById(bid.getAuction().getId());
        System.out.println("------------------- Entré a actualizar la auction ---------------------------------");
        System.out.println("Price: " + auction.getTicket().getPrice() + " - Amount: " + bid.getAmount());
        if (auction.getTicket().getPrice() >= bid.getAmount()) {
            throw new InconsistentBid("Bid inconsistente - Precio");
        } else {
            try {
                ticketRepository.updatePrice(bid.getAmount(), auction.getTicket().getId());
            } catch (Exception e) {
                System.out.println("Se feliz");
            }
            bidRepository.save(bid);
        }
    }
    
    /**
     * Obtiene todas las auctions filtrando por aerolinea
     * @param airline Nombre de la aerolinea.
     * @return Lista de todas las auctions relacionadas a esa aerolinea.
    */
    public List<Auction> getAllAuctionByAirline(String airline){
        List<Auction> auctions = new ArrayList<>();
        List<Auction> all = getAllAuctions(new HashMap<String, String>());
        for(Auction auction : all){
            if(auction.getTicket().getFlight().getAirline().getName().equals(airline)){
                auctions.add(auction);
            }
        }
        return auctions;
    }
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
}