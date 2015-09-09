package br.com.aline.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

/**
 * Classe que contém método main com configurações do spring boot.
 * 
 * @author aline.dias
 *
 */
@ComponentScan(basePackages = "br.com.aline.api")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "br.com.aline.api.repository")
@EntityScan(basePackages = "br.com.aline.api.model")
@PropertySource("classpath:application.properties")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
