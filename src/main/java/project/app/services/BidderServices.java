package project.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.app.exception.BidderForbidden;
import project.app.exception.BidderNotFound;
import project.app.model.Auction;
import project.app.model.Bid;
import project.app.model.Bidder;
import project.app.persistence.BidderRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * BidderServices
 */
@Service
public class BidderServices {

    @Autowired
    private BidderRepository bidderRepository;


    /**
     * Agrega un usuario a la base de datos.
     * @param bidder Objeto que representa el nuevo usuario.
     * @throws BidderForbidden Se genera en caso de error, esto se puede presentar por usar un correo que ya esta en uso dentro del sistema o por usar un username que ya se encuentra registrado.
     */
    public void addAccount(Bidder bidder) throws BidderForbidden {
        Bidder bidderDBMail = bidderRepository.findByMail(bidder.getMail());
        Bidder bidderDBUsername = bidderRepository.findByUsername(bidder.getUsername());
        if(bidderDBMail!=null){
            throw new BidderForbidden("Este correo se encuentra en uso");
        }else if(bidderDBUsername!=null){
            throw new BidderForbidden("Username ya se encuentra en uso");
        }
        bidder.setBids(new HashSet<Bid>());
        bidderRepository.save(bidder);        
    }

    /**
     * Obtiene un usuario especifico, buscando en base a su username.
     * @param username Username que identifica al usuario dentro del sistema.
     * @return Retorna el usuario asociado al username que se nos solicito.
     * @throws BidderNotFound Si no se encuentra ningún usuario asociado a dicho username.
     */
    public Bidder getBidder(String username) throws BidderNotFound {
        Bidder bidder = null;
        Bidder bidderDB = bidderRepository.findByUsername(username);
        if(bidderDB==null){
            throw new BidderNotFound("Usuario no encontrado");
        }
        bidder = bidderDB;
        bidder.setBids(null);
        return bidder;
    }

    /**
     * 
     * @param bidder
     * @param name
     */
    public void updateBidder(Bidder bidder,String name){
        // TO DO
    }

    /**
     * Metodo que permite el aumento y disminución de saldo dentro de un usuario.
     * @param amount    Cantidad que se desea agregar/quitar del saldo.
     * @param username  Identificador del usuario al que se desea añadirle el saldo.
     * @throws BidderNotFound   En caso de no encontrar al bidder que se intenta modificar.
     */
    public void updateBalance(float amount, String username) throws BidderNotFound {
        Bidder bidder = getBidder(username);
        float previousBalance = bidder.getBalance();
        float newBalance = previousBalance+amount;
        bidder.setBalance(newBalance);
        bidderRepository.save(bidder);
    }
    
    /**
     * Retorna todas las subastas activas de un usuario (por activas se entiende aquellas en las que tiene una puja realizada).
     * @param username Identificador del usuario
     * @return Conjunto de subastas
     * @throws BidderNotFound En caso de que no se al usuario en cuestion.
     */
    public Set<Auction> getAuctionsPerUser(String username) throws BidderNotFound {
        Set<Auction> auctions = new HashSet<>();
        Bidder bidderDB = bidderRepository.findByUsername(username);
        if(bidderDB == null){
            throw new BidderNotFound("Usuario no encontrado.");
        }
        for (Bid bid : bidderDB.getBids()) {
            bid.setBidder(null);
            auctions.add(bid.getAuction());
        }
        return auctions;
    }


    /**
     * Valida si el usuario cuenta con fondos suficientes para realizar una oferta.
     * @param bid
     * @return
     * @throws BidderNotFound
     */
    public boolean validateBid(Bid bid) throws BidderNotFound {
        Bidder bidder = getBidder(bid.getBidder().getUsername());
        bidder.setBids(new HashSet<Bid>());
        bid.setBidder(bidder);
        return bidder.getBalance()>=bid.getAmount();
    }
    
}