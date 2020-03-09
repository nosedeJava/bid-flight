package project.app.model;

import javax.persistence.Entity;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String type;
    @ManyToOne
    @JoinColumn(name = "flight")
    private Flight flight;
    private float price;
    private String bagTyte;

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

    public String getBagTyte() {
        return bagTyte;
    }

    public void setBagTyte(String bagTyte) {
        this.bagTyte = bagTyte;
    }

    @Override
    public String toString() {
        return "Ticket [bagTyte=" + bagTyte + ", flight=" + flight + ", id=" + id + ", price=" + price + ", type="
                + type + "]";
    }
    
}