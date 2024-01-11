package es.codeurjc.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${security.user}")
	private String user;

	@Value("${security.encodedPassword}")
	private String encodedPassword;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		PasswordEncoderFactories.createDelegatingPasswordEncoder();

		auth.inMemoryAuthentication().withUser(user).password("{bcrypt}" + encodedPassword).roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Public pages
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginerror").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();

		// Private pages (all other pages)
		http.authorizeRequests().anyRequest().authenticated();

		// Login form
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("username");
		http.formLogin().passwordParameter("password");
		http.formLogin().defaultSuccessUrl("/private");
		http.formLogin().failureUrl("/loginerror");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");

		// Disable CSRF at the moment
		http.csrf().disable();
	}

}
