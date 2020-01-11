package com.lonix.det;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={
		"com.lonex.*" , "com.lonix.det.controllers"})
public class DETApplication extends SpringBootServletInitializer{

	   @Override

	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {

	      return application.sources(DETApplication.class);

	   }
	public static void main(String[] args) {
		SpringApplication.run(DETApplication.class, args);
	}

}
