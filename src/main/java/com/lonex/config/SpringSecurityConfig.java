package com.lonex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{


		
	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/data/**");
	        web.ignoring().antMatchers("/static/**");
	}

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    

		    http.authorizeRequests().antMatchers("/**").permitAll();
		    
	    	http.httpBasic().and().authorizeRequests()
        .antMatchers("/admin/log").hasRole("ADMIN").and().csrf().disable();
	    
	    
	    http.csrf().disable();
	       }
	        
	       
	  

	 // In-memory authentication to authenticate the user i.e. the user credentials are stored in the memory.
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication().withUser("DET").password("AMLX").roles("ADMIN");
	    }


	
}
