package project.app.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import project.app.model.Bidder;

/**
 * BidderDetailsImpl
 */
public class BidderDetailsImpl implements UserDetails {
    
    private Bidder bidder;
    private float balance;
    private String email;
    private String names;
    private String lastnames;
    private String documenttype;
    private String document;
    /**
     *
     */
    private static final long serialVersionUID = 6775216925057034028L;

    public BidderDetailsImpl(Bidder bidder) {
        this.bidder = bidder;
        this.balance = bidder.getBalance();
        this.email = bidder.getMail();
        this.names = bidder.getNames();
        this.lastnames = bidder.getLastnames();
        this.documenttype = bidder.getDocumenttype();
        this.document = bidder.getDocument();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(bidder.getRole());
    }

    @Override
    public String getPassword() {
        return bidder.getPassword();
    }

    @Override
    public String getUsername() {
        return bidder.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastnames() {
        return lastnames;
    }

    public void setLastnames(String lastnames) {
        this.lastnames = lastnames;
    }

    public String getDocumenttype() {
        return documenttype;
    }

    public void setDocumenttype(String documenttype) {
        this.documenttype = documenttype;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    
}