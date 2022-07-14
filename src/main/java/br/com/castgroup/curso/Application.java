package br.com.castgroup.curso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import br.com.castgroup.curso.service.AuditingService;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditingService")
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOGGER.info("Startando Projeto");
		SpringApplication.run(Application.class, args);
		LOGGER.info("Projeto Iniciado com Sucesso");
	}
	
	@Bean
    AuditorAware<String> auditorProvider() {
     return new AuditingService();
    }

}
