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
    /**
     *
     */
    private static final long serialVersionUID = 6775216925057034028L;

    public BidderDetailsImpl(Bidder bidder) {
        this.bidder = bidder;
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

    
}