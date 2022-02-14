package com.adeeb.demo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.adeeb.demo", "org.modelmapper.ModelMapper"})
public class SimpleuserregisterationApplication extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(SimpleuserregisterationApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
