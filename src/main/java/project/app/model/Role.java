package project.app.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    AIRLINE, BIDDER;

    @Override
    public String getAuthority() {
        return name();
    }
}