package com.solidarity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication()
@EnableJpaRepositories
public class SolidarityApplication extends  SpringBootServletInitializer{

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SolidarityApplication.class);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(SolidarityApplication.class, args);
	}
	
	

}

