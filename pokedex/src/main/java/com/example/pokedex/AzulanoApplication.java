package com.example.pokedex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@Controller
@OpenAPIDefinition(
	info = @Info(
		title = "Azulano",
		summary = "API Azulano",
		description = "Um app que identifica o animal marinho atraves da imagem",
		version = "1.0.0",
		contact = @Contact(name = "João Vitor", email = "joaovitorvicentebenjamin@gmail.com")
	)
)
public class AzulanoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzulanoApplication.class, args);
	}

}
