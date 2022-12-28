package com.aom.shortener;

import com.aom.shortener.domain.entities.Shortener;
import com.aom.shortener.domain.repositories.ShortenerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SpringBootApplication
@RestController
public class ShortenerApplication {

	@GetMapping("/")
	public String hello () { return "Hello word!";}

	@Bean
	public CommandLineRunner init(@Autowired ShortenerRepository shortenerRepository) {
		return args -> {
			System.out.println("Salvando clientes");

			Shortener shortener = new Shortener("http://test.com", "t.c/10");

			System.out.println("Salvando shortener");
			shortenerRepository.save(shortener);
			System.out.println("Shortener salvo");


			Optional<Shortener> shortenerOptional = shortenerRepository.findBySourceUrl(shortener.getSourceUrl());

			shortenerOptional.ifPresent(System.out::println);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}

}
