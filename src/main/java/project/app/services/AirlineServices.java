package project.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.app.exception.AirlineBadRequest;
import project.app.exception.AirlineForbidden;
import project.app.exception.AirlineNotFound;
import project.app.exception.FlightNotFound;
import project.app.model.Airline;
import project.app.model.Flight;
import project.app.model.Ticket;
import project.app.persistence.AirlineRepository;
import project.app.persistence.FlightRepository;

/**
 * AirlineServices
 */
@Service
public class AirlineServices {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AuctionServices auctionServices;
    
    /**
     * Retorna una aerolinea buscandola por el nombre.
     * @param name Nombre identificador de la aerolinea.
     * @return La aerolinea encontrada en la base de datos.
     * @throws AirlineNotFound En caso de que la aerolinea no este dentro de la base de datos.
     */
    public Airline getAirlineByName(String name) throws AirlineNotFound {
        Airline airlineDB = airlineRepository.findByName(name);
        if(airlineDB==null){
            throw new AirlineNotFound("Aerolinea no encontrada");
        }
        return airlineDB;
    }

    /**
     * Añadir una aerolinea, pensado para el registro/creación interno de
     * aerolineas.
     * 
     * @param airline El objeto que representa la nueva aerolinea que quiere ser
     *                registrada.
     * @throws AirlineForbidden En caso de error lanzará un "Operación no permitida"
     */
    public void addAirline(Airline airline) throws AirlineForbidden {
        try {
            airlineRepository.save(airline);
        } catch (Exception e) {
            throw new AirlineForbidden("Fallo dentro de la operación de creación del Airline");
        }
    }

    /**
     * Actualización de la información de una aerolinea.
     * 
     * @param name    Identificador dentro del sistema de la aerolinea que se desea
     *                modificar.
     * @param airline Objeto que representa la aerolinea con los cambios hechos.
     * @throws AirlineForbidden
     * @throws AirlineBadRequest
     * @throws AirlineNotFound
     */
    public void updateAirline(String name, Airline airline)
            throws AirlineForbidden, AirlineBadRequest, AirlineNotFound {
        if (!name.equals(airline.getName())) {
            throw new AirlineBadRequest("Datos incosistentes");
        }
        Airline airlineFromDB = airlineRepository.findByName(name);
        if (airlineFromDB == null) {
            throw new AirlineNotFound("Aerolinea no encontrada");
        }
        airlineFromDB.setInformation(airline.getInformation());
        airlineRepository.save(airlineFromDB);
    }


    /**
     * Retorna los vuelos que tiene una aerolinea en especifico
     * @param airline Nombre de la aerolinea, que hace las veces de identificador dentro del sistema.
     * @return  Retorna todos los vuelos de la aerolinea.
     * @throws AirlineNotFound En caso de que el nombre que se recibe no exista, se lanzará esta excepción.
     */
    public Set<Flight> getAirlineFlights(String airline) throws AirlineNotFound {
        Set<Flight> flights = new HashSet<>();
        Airline airlineDB = airlineRepository.findByName(airline);
        if(airlineDB==null){
            throw new AirlineNotFound("Aerolinea inexistente");
        }
        ArrayList<Flight> flightsFromDB = (ArrayList<Flight>) flightRepository.findAll();
        for (Flight flight : flightsFromDB) {
            if(flight.getAirline().getName().equals(airline)){
                flights.add(flight);
            }
        }
        for (Flight flight : flights) {
            for (Ticket ticket : flight.getTickets()) {
                ticket.setFlight(null);
            }
        }
        flights = filterByDate(flights);
        return flights;
    }
    

    /**
     * Recibe un vuelo, lo registra y crea las subastas asociadas a él.
     * @param airline Identificador de la aerolinea dentro del sistema.
     * @param flight Objeto vuelo que contiene la información necesaria para el registro.
     * @throws AirlineNotFound Si la aerolinea no se encuentra en base de datos.
     * @throws AirlineForbidden Si se intenta crear un vuelo con una aerolinea diferente a la solicitada.
     */
    public void addFlight(String airline, Flight flight) throws AirlineNotFound, AirlineForbidden {
        Airline airlineDB = airlineRepository.findByName(airline);
        if(airlineDB==null){
            throw new AirlineNotFound("Aerolinea inexistente");
        }
        if(!flight.getAirline().getName().equals(airlineDB.getName())){
            throw new AirlineForbidden("Acción no permitida - Creación de un vuelo con una aerolinea inconsistente");
        }
        auctionServices.addAuction(flight);
    }

    /**
     * Retorna un vuelo en especifico dentro de los vuelos de una aerolinea.
     * @param airline Nombre de la aerolinea
     * @param id Identificador del vuelo
     * @return El vuelo solicitado
     * @throws AirlineNotFound Si la aerolinea no existe
     * @throws FlightNotFound Si el vuelo no existe
     * @throws AirlineForbidden Si los datos entre la aerolinea solicitada y la aerolinea registrada dentro del vuelo resultan ser incosistentes.
     */
    public Flight getAirlineFlightById(String airline, int id) throws AirlineNotFound, FlightNotFound,
            AirlineForbidden {
        Flight flight = null;
        Airline airlineDB = airlineRepository.findByName(airline);
        if(airlineDB==null){
            throw new AirlineNotFound("Aerolinea inexistente");
        }
        Flight flightDB = flightRepository.findById(id);
        if(flightDB == null){
            throw new FlightNotFound("Vuelo no encontrado");
        }
        if(!flightDB.getAirline().getName().equals(airline)){
            throw new AirlineForbidden("Datos inconsistentes");
        }
        flight = flightDB;
        for (Ticket ticket : flight.getTickets()){
            ticket.setFlight(null);            
        }
        return flight;
    }


    /**
     * Elimina de la base de datos un vuelo especifico
     * @param airline Nombre de la aerolinea
     * @param id Identificador del vuelo
     * @throws AirlineNotFound Si la aerolinea no existe
     * @throws FlightNotFound Si el vuelo no existe
     */
    public void removeAirlineFlight(String airline, int id) throws AirlineNotFound, FlightNotFound {
        Airline airlineDB = airlineRepository.findByName(airline);
        if(airlineDB==null){
            throw new AirlineNotFound("Aerolinea inexistente");
        }
        Flight flightDB = flightRepository.findById(id);
        if(flightDB == null){
            throw new FlightNotFound("Vuelo no encontrado");
        }
        flightRepository.delete(flightDB);
    }


    /**
     * Retorna un vuelo especifico
     * @param id Identificador
     * @return Vuelo solicitado
     * @throws FlightNotFound En caso de no encontrar el vuelo
     */
    public Flight getFlightById(int id) throws FlightNotFound {
        Flight flightResult = null;
        Flight flightDB = flightRepository.findById(id);
        if(flightDB == null){
            throw new FlightNotFound("Vuelo no encontrado");
        }
        // Control para la recurrencia infinita
        for (Ticket ticket : flightDB.getTickets()) {
            ticket.setFlight(null);
        }
        flightResult = flightDB;
        return flightResult;
    }


    /**
     * Filtro para las fechas.
     * @param flights
     * @return
     */
    private Set<Flight> filterByDate(Set<Flight> flights){
        Date today = new Date(System.currentTimeMillis());
        for (Flight flight : flights) {
            Date flightDate = flight.getTakeoffdate();
            if(today.compareTo(flightDate)>=0){
                flights.remove(flight);
            }
        }
        return flights;
    }
}