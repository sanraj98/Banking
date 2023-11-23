package com.sys.Banking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.sys.service.IuserService;

@Configuration
public class BankSecurityCofig {
	
	   @Bean
	    public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	   
	   @Bean
	   public AuthenticationSuccessHandler successHandler() {
	     SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
	     handler.setDefaultTargetUrl("/home");
	     handler.setUseReferer(false);
	     return handler;
	   }
	   
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider(IuserService userService) {
	        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	        auth.setUserDetailsService(userService); //set the custom user details service
	        auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
	        return auth;
	    }
	
	    
	 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(configurer ->
        configurer
        		.requestMatchers("/home").hasRole("EMPLOYEE")
                .requestMatchers("/register/**").permitAll()
                .anyRequest().authenticated()
)
.formLogin(form ->
        form
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .successHandler(successHandler())
                .permitAll()
)
.logout(logout -> logout.permitAll()
)
.exceptionHandling(configurer ->
        configurer.accessDeniedPage("/access-denied")
);

return http.build();
    }

}
