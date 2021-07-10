package com.eeilander.restfulwebservicemm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
// @ComponentScan("com.eeilander.restfulwebservicemm.web")
// @SpringBootApplication(scanBasePackages = {"com.eeilander.restfulwebservicemm.web"})
@SpringBootApplication
public class RestfulwebservicemmApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulwebservicemmApplication.class, args);
	}

}
