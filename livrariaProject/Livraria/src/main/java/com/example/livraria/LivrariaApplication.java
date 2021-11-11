package com.example.livraria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityDataConfiguration;

/**
 * Classe Main da aplicação.
 * 
 * @author Agemiro Neto
 * @author Jordielson Silva
 * @author Victor Macêdo
 */

@SpringBootApplication
public class LivrariaApplication {
	public static void main(String[] args) {
		SpringApplication.run(LivrariaApplication.class, args);
	}
}
