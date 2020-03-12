package project.app.model;

/**
 * Bid
 */
public class Bid {

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
    
}