package project.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * SecurityConfig
*/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    @Qualifier("customAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception 
    {
        http
        .logout()
        .and()
        .csrf().disable()
        .cors().disable()
        .formLogin()
        .permitAll()
        .successHandler(authenticationSuccessHandler);


        http
          .csrf().disable()
          .authorizeRequests()
          .antMatchers("/auctions**").hasAnyAuthority("BIDDER","AIRLINE")
          .antMatchers("/accounts").permitAll()
          .antMatchers("/accounts/**").hasAuthority("BIDDER")
          .antMatchers("/airlines").permitAll()
          .antMatchers("/airlines/**").permitAll()
          .antMatchers("/flights**").hasAnyAuthority("BIDDER","AIRLINE")
          .antMatchers("/particular-auction.html").hasAuthority("BIDDER")
          .antMatchers("/auctions.html").hasAuthority("BIDDER")
          .antMatchers("/airlines.html").permitAll()
          .antMatchers("/login**").permitAll()
          .antMatchers("/about-us.html").permitAll()
          .antMatchers("/","/index.html").permitAll()
          .antMatchers("/register**").permitAll()
          .and()
          .formLogin()
          .loginPage("/login.html")
          .loginProcessingUrl("/perform_login")
          .defaultSuccessUrl("/auctions.html", true)
          .and()
          .logout()
          .logoutUrl("/perform_logout")
          .deleteCookies("JSESSIONID");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}