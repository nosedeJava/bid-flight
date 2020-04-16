package project.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import project.app.model.Bidder;
import project.app.persistence.BidderRepository;

/**
 * BidderDetailsServiceImpl
 */

@Service
public class BidderDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BidderRepository bidderRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Bidder bidder = bidderRepository.findByUsername(username);
        if(bidder == null){
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        UserDetails bidderDetails = new BidderDetailsImpl(bidder);
        return bidderDetails;
    }

    
}