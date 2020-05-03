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
 * Bid
 */
@Entity
@Table(name = "bid")
public class Bid {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bidder")
    private Bidder bidder;
    private float amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auction")
    private Auction auction;

    public Bid() {
    }

    public Bid(Bidder bidder, float amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public void setBidder(Bidder bidder) {
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
        return "Bid [amount=" + amount + ", bidder=" + bidder + ", auction="+ auction+ "]";
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
    
}