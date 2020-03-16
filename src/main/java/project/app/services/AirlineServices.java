package project.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.app.exception.AirlineBadRequest;
import project.app.exception.AirlineForbidden;
import project.app.exception.AirlineNotFound;
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


    public void updateAirline(String name, Airline airline) throws AirlineForbidden, AirlineBadRequest,
            AirlineNotFound {
        try{
            if(!name.equals(airline.getName())){
                throw new AirlineBadRequest("Datos incosistentes");
            }
            Airline airlineFromDB = airlineRepository.findByName(name);
            if(airlineFromDB==null){
                throw new AirlineNotFound("Aerolinea no encontrada");
            }
            airlineFromDB.setInformation(airline.getInformation());
            airlineRepository.save(airlineFromDB);
        }catch(AirlineNotFound e){
            throw new AirlineNotFound("Aerolinea no encontrada");
        }catch(AirlineBadRequest e){
            throw new AirlineBadRequest("Datos incosistentes");
        }catch(Exception e){
            throw new AirlineForbidden("Operación fallida - Acción no permitida");
        }
    }

    
}