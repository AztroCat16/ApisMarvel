package com.challenge.apismarvel;

import com.challenge.apismarvel.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class ApismarvelApplication {

	@Autowired
	private AppConfig config;

	public static void main(String[] args) {
		SpringApplication.run(ApismarvelApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String regex = "\\s*,\\s*";

				registry.addMapping("/**")
						.allowedOrigins(config.getAllowedOrigins())
						.allowedMethods(config.getAllowedMethods().split(regex))
						.allowedHeaders(config.getAllowedHeaders().split(regex))
						.exposedHeaders(config.getExposedHeaders().split(regex));
			}
		};
	}

}
