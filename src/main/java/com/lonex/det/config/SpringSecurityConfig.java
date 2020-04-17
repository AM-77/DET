package com.lonex.det.config;

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
		web.ignoring().antMatchers("/assets/**");
	}

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
		  http.authorizeRequests().antMatchers("/static/**", "/data/**", "/*" ,"/Category/**" , "/login" ,"/Search*").permitAll()
		  .antMatchers("/admin/**" , "/admin/addMachine").hasRole("ADMIN")
		  .anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
		  .and().logout().permitAll();
		  http.csrf().disable();
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication().withUser("DET").password("Det_Web_Add00").roles("ADMIN");
	    }
	
}
