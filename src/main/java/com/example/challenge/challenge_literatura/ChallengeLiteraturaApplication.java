package com.example.challenge.challenge_literatura;

import com.example.challenge.challenge_literatura.principal.Principal;
import com.example.challenge.challenge_literatura.repository.AutorRepository;
import com.example.challenge.challenge_literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository repository;
	@Autowired
	private LibroRepository repository2;


	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository,repository2);
//		Principal principal = new Principal();
		principal.muestraElMenu();
	}
}
