package com.example.smartBar.smartBar;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
		info = @Info(
				title = "Smart Bar",
				description = "Smart Bar Rest Documentation",
				version = "v1.0",
				contact = @Contact(
						name ="Ademola Plumptre",
						email="info@overallheuristic.com",
						url ="http://www.overallheuristic.com"
				)
//				license = @License(
//						name = "Apache",
//						url ="http://www.google.com"
//				)
		)
//		externalDocs = @ExternalDocumentation(
//				description = "SmartBar for developers",
//				url ="https://www.google.com"
//		)
)
@SpringBootApplication
public class SmartBarApplication {

@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SmartBarApplication.class, args);
	}

}
