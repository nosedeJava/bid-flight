package project.app.config;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import project.app.model.Bidder;
import project.app.persistence.BidderRepository;

/**
 * CustomAuthenticationSuccessHandler
 */
@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private BidderRepository bidderRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if(roles.contains("BIDDER")){
            Bidder bidder = bidderRepository.findByUsername(authentication.getName());
            if(bidder == null) throw new ServletException("Usuario inexistente");
            //request.getSession().setAttribute("username", bidder.getUsername());
            response.sendRedirect("auction.html");
        }else{
            response.sendRedirect("airlines.html");
        }
    }

    
}