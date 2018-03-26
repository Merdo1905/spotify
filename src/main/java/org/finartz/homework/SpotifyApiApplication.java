package org.finartz.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpotifyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotifyApiApplication.class, args);
	}
}
