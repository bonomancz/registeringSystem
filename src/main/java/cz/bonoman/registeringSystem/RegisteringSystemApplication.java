package cz.bonoman.registeringSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cz.bonoman.controller", "cz.bonoman.system", "cz.bonoman.service"})
public class RegisteringSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisteringSystemApplication.class, args);
	}

}
