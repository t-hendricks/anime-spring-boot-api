package com.favAnime.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyFavAnimeApplication {

	/**
	 * Configure embedded Tomcat.
	 * Enable Spring MVC default setup.
	 * Configure and set up the Jackson for JSON.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(MyFavAnimeApplication.class, args);
	}

}
