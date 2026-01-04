package rs.ac.singidunum.servelogic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import rs.ac.singidunum.servelogic.utility.devDataInit.FusekiTest;

@SpringBootApplication
public class ServelogicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServelogicApplication.class, args);
		FusekiTest.init();
	}

}
