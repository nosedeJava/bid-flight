package project.app.model;

/**
 * Payment
 */
public class Payment {

    private int id;
    private User user;
    private String type;
    private String info;

    public Payment() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Payment [id=" + id + ", info=" + info + ", type=" + type + ", user=" + user + "]";
    }
    
}