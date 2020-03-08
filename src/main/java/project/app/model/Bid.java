package project.app.model;

/**
 * Bid
 */
public class Bid {

    private User user;
    private float amount;

    public Bid() {
    }

    public Bid(User user, float amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bid [amount=" + amount + ", user=" + user + "]";
    }
    
}