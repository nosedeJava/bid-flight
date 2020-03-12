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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Auction
 */
@Entity
@Table(name = "auction")
public class Auction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;
    private Ticket ticket;
    private Date dueDate;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auction")
    private Set<Bid> bids;
    // private List<Bidder> followers;

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

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    /*public List<Bidder> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Bidder> followers) {
        this.followers = followers;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
}