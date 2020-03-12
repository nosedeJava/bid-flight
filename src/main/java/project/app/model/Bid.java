package project.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Bid
 */
@Entity
@Table(name = "bid")
public class Bid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    @ManyToOne
    @JoinColumn(name = "auction")
    private Auction auction;
    @ManyToOne
    @JoinColumn(name = "bidder")
    private Bidder bidder;
    private float amount;

    public Bid() {
    }

    public Bid(Bidder bidder, float amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public void setUser(Bidder bidder) {
        this.bidder = bidder;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bid [amount=" + amount + ", bidder=" + bidder + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBidder(Bidder bidder) {
        this.bidder = bidder;
    }
    
}