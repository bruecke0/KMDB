package com.example.filmsociety;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FilmsocietyApplication {

	public static void main(String[] args) {
		System.out.println("lol");
		SpringApplication.run(FilmsocietyApplication.class, args);
		System.out.println("lol");
	}

	@GetMapping
	public String putsisOn(){
		return "miks sa ei toota";
	}

}
