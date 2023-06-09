package com.CrudRegistro;

//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@ComponentScan(basePackages = "com.CrudRegistro")
public class CrudRegistroApplication {
	public static void main(String[] args) {
		SpringApplication.run(CrudRegistroApplication.class, args);
	}

	
}
