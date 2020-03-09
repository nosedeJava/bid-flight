package project.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Flight
 */
@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String airline;
    private Date takeOfffDate;
    private int duration;
    private String source;
    private String destiny;
    @OneToMany
    @JoinColumn(name = "flight")
    private Set<Layover> layovers;
    @OneToMany
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

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Date getTakeOfffDate() {
        return takeOfffDate;
    }

    public void setTakeOfffDate(Date takeOfffDate) {
        this.takeOfffDate = takeOfffDate;
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
                + ", layovers=" + layovers + ", source=" + source + ", takeOfffDate=" + takeOfffDate + "]";
    }
    

}