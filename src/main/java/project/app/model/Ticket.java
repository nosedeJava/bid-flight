package project.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Ticket
 */
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    private String type;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "flight")
    private Flight flight;
    private float price;
    private String bagtype;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBagtype() {
        return bagtype;
    }

    public void setBagtype(String bagtype) {
        this.bagtype = bagtype;
    }

    @Override
    public String toString() {
        return "Ticket [bagTyte=" + bagtype + ", flight=" + flight + ", id=" + id + ", price=" + price + ", type="
                + type + "]";
    }
    
}