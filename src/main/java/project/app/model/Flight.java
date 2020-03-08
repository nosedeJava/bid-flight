package project.app.model;

import java.util.Date;
import java.util.List;

/**
 * Flight
 */
public class Flight {

    private int id;
    private String airline;
    private Date takeOfffDate;
    private int duration;
    private String source;
    private String destiny;
    private List<Layover> layovers;

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public Date getTakeOfffDate() {
        return takeOfffDate;
    }

    public void setTakeOfffDate(Date takeOfffDate) {
        this.takeOfffDate = takeOfffDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public List<Layover> getLayovers() {
        return layovers;
    }

    public void setLayovers(List<Layover> layovers) {
        this.layovers = layovers;
    }

    @Override
    public String toString() {
        return "Flight [airline=" + airline + ", destiny=" + destiny + ", duration=" + duration + ", id=" + id
                + ", layovers=" + layovers + ", source=" + source + ", takeOfffDate=" + takeOfffDate + "]";
    }
    
    

}