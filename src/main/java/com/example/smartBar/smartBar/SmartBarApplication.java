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
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;

@OpenAPIDefinition(
		info = @Info(
				title = "Smart Bar",
				description = "Smart Bar Rest Documentation",
				version = "v1.0",
				contact = @Contact(
						name ="Ademola Plumptre",
						email="info@overallheuristic.com",
						url ="http://www.overallheuristic.com"
				),
				license = @License(
						name = "Overall Heuristic",
						url ="http://www.overallheuristic.com"
				)
		),
		security = @SecurityRequirement(name = "bearerAuth"),
		externalDocs = @ExternalDocumentation(
				description = "SmartBar for developers",
				url ="http://www.overallheuristic.com"
		)
)
@SecuritySchemes({
		@SecurityScheme(
				name = "bearerAuth",
				type = SecuritySchemeType.HTTP,
				scheme = "bearer",
				bearerFormat = "JWT"
		)
})

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
