package project.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Flight
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    @ManyToOne
    @JoinColumn(name = "airline")
    private Airline airline;
    private Date takeoffdate;
    private int duration;
    private String source;
    private String destiny;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight")
    private Set<Layover> layovers;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight")
    private Set<Ticket> tickets;

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Date getTakeoffdate() {
        return takeoffdate;
    }

    public void setTakeoffdate(Date takeoffdate) {
        this.takeoffdate = takeoffdate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public Set<Layover> getLayovers() {
        return layovers;
    }

    public void setLayovers(Set<Layover> layovers) {
        this.layovers = layovers;
    }

    

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
    

    @Override
    public String toString() {
        return "Flight [airline=" + airline + ", destiny=" + destiny + ", duration=" + duration + ", id=" + id
                + ", source=" + source + ", takeOfffDate=" + takeoffdate + "]";
    }
    

}