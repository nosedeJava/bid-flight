package project.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.app.exception.BidderForbidden;
import project.app.exception.BidderNotFound;
import project.app.model.Bidder;
import project.app.persistence.BidderRepository;

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
        bidder.setPayments(null);
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
        return bidder;
    }

    
}