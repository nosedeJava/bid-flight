package project.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @OneToOne( cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "ticket")
    private Ticket ticket;
    private Date duedate;
    @OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name = "auction")
    private Set<Bid> bids;

    public Auction(Ticket ticket, Date dueDate) {
        this.ticket = ticket;
        this.duedate = dueDate;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Date getDueDate() {
        return duedate;
    }

    public void setDueDate(Date dueDate) {
        this.duedate = dueDate;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Auction [duedate=" + duedate + ", id=" + id + ", ticket=" + ticket + "]";
    }

    public Auction() {
    }

    
}