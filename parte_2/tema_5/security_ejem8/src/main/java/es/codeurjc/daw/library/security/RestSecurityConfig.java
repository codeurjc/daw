package es.codeurjc.daw.library.security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        // Private endpoints
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/books/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/books/**").hasRole("USER");
        http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/books/**").hasRole("ADMIN");	
    		
        // Other endpoints are public
        http.authorizeRequests().anyRequest().permitAll();

        // Disable CSRF protection (it is difficult to implement in REST APIs)
        http.csrf().disable();

        // Enable Basic Authentication
        http.httpBasic();
    		
        // Disable Form login Authentication
        http.formLogin().disable();

        // Avoid creating session (because every request has credentials) 
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
    }
}
