package com.aom.shortener;

import com.aom.shortener.domain.entities.Shortener;
import com.aom.shortener.domain.repositories.ShortenerRepository;
import com.aom.shortener.domain.services.ShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@SpringBootApplication
public class ShortenerApplication {

	@Bean
	public CommandLineRunner init(@Autowired ShortenerService service) {
		return args -> {
			String url = "https://www.google.com1";

			service.create(url);

			Shortener shortener = service.findBySourceUrl(url);

			System.out.println(shortener);
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ShortenerApplication.class, args);
	}

}
