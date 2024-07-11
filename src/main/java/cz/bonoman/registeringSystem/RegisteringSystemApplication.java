/*
################################################################################################
# REGISTERING SYSTEM
#
# It defines REST API
# It contains user WEB GUI available at http://localhost:8080
# It contains Postman testing data export in file RegisteringSystem.postman_collection.json
# It contains database sql dump in file dump-registeringSystem-202407111826.sql
#
# Author: Jan Novotny, <23.6.2024>
# mail: bonoman@volny.cz
# Discord: bonoman_cz [Bonoman#0823], HonzaN
################################################################################################
 */


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
