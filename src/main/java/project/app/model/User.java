package project.app.model;

/**
 * User
 */
public class User {

    private String mail;
    private String password;
    private String names;
    private String lastNames;
    private String username;
    private String documentType;
    private String document;
    private float balance;

    public User(){

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastNames() {
        return lastNames;
    }

    public void setLastNames(String lastNames) {
        this.lastNames = lastNames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User [balance=" + balance + ", document=" + document + ", documentType=" + documentType + ", lastNames="
                + lastNames + ", mail=" + mail + ", names=" + names + ", password=" + password + ", username="
                + username + "]";
    }
    
}