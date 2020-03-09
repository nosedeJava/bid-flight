package project.app.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Layover
 */
@Entity
@Table(name = "layover")
public class Layover {

    @Id
    private int id;
    @ManyToOne
    @JoinColumn(name = "flight")
    private Flight layover;
    private String airport;

    public Layover() {
    }

    public Flight getLayover() {
        return layover;
    }

    public void setLayover(Flight layover) {
        this.layover = layover;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "Layover [airport=" + airport + ", layover=" + layover + "]";
    }

    
}