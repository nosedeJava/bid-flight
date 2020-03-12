package project.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * User
 */
@Entity
@Table(name = "bidder")
public class User {
    @Id
    private String mail;
    private String password;
    private String names;
    private String lastnames;
    private String username;
    private String documenttype;
    private String document;
    private float balance;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "bidder")
    private Set<Payment> payments;

    public User(){

    }

    public User(String mail, String password, String names, String lastNames, String username, String documentType,
            String document, float balance, Set<Payment> payments) {
        this.mail = mail;
        this.password = password;
        this.names = names;
        this.lastnames = lastNames;
        this.username = username;
        this.documenttype = documentType;
        this.document = document;
        this.balance = balance;
        this.payments = payments;
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
        return lastnames;
    }

    public void setLastNames(String lastNames) {
        this.lastnames = lastNames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocumentType() {
        return documenttype;
    }

    public void setDocumentType(String documentType) {
        this.documenttype = documentType;
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
        return "User [balance=" + balance + ", document=" + document + ", documentType=" + documenttype + ", lastNames="
                + lastnames + ", mail=" + mail + ", names=" + names + ", password=" + password + ", username="
                + username + "]";
    }

    
    
}