package project.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.app.exception.AirlineForbidden;
import project.app.model.Airline;
import project.app.persistence.AirlineRepository;

/**
 * AirlineServices
 */
@Service
public class AirlineServices {

    @Autowired
    private AirlineRepository airlineRepository;


    /**
     * 
     * @param airline
     * @throws AirlineForbidden
     */
    public void addAirline(Airline airline) throws AirlineForbidden{
        try{
            airlineRepository.save(airline);
        }catch(Exception e){
            throw new AirlineForbidden("Fallo dentro de la operación de creación del Airline");
        }
    }

    
}