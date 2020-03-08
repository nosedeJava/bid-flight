package project.app.model;

/**
 * Layover
 */
public class Layover {
    
    private Flight layover;
    private String airport;

    public Layover() {
    }

    public Flight getLayover() {
        return layover;
    }

    public void setLayover(Flight layover) {
        this.layover = layover;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return "Layover [airport=" + airport + ", layover=" + layover + "]";
    }

    
}