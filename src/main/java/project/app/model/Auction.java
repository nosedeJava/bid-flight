package project.app.model;

import java.util.Date;
import java.util.List;

/**
 * Auction
 */
public class Auction {

    private Ticket ticket;
    private Date dueDate;
    private List<Bid> bids;
    private List<User> followers;

    public Auction() {
    }

    public Auction(Ticket ticket, Date dueDate) {
        this.ticket = ticket;
        this.dueDate = dueDate;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    
}