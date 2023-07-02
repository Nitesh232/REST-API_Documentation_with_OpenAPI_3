package com.nitesh.springboot.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;



@Configuration

@OpenAPIDefinition(
		info = @Info(title = "Ticket Booking Controller.", 
		version = "1.0.0", 
		contact = @Contact(name = "Nitesh Mishra", email = "hnitesh.work@gmail.com", url = "https://github.com/Nitesh232"), 
		description = "A Demo RestAPI by which you can book, Update and Delete your Train Ticket.", 
		license = @License(name = "Apache License.", url = "https://www.apache.org/licenses/LICENSE-2.0")),
		servers = {@Server(description = "Local Environment", url = "http://localhost:8080/"),
				   @Server(description = "Production Environment", url = "https://github.com/Nitesh232")}
)
public class SwaggerConfig {
	
}
