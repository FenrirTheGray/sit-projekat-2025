package rs.ac.singidunum.servelogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServelogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServelogicApplication.class, args);
		
		System.out.println("Running on port: %s".formatted(System.getenv().getOrDefault("REST_PORT", "7999")));
	}
	
}
