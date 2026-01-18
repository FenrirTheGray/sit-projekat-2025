package rs.ac.singidunum.servelogic.utility;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class FusekiDBUtility {
	
	private final String host;
	private final int port;
	private final String dataset;
	private final HttpClient httpClient;
	private RDFConnection connection;

	public FusekiDBUtility() {
		this.host = System.getenv().getOrDefault("FUSEKI_HOST", "localhost");
		this.port = Integer.parseInt(System.getenv().getOrDefault("FUSEKI_PORT", "3030"));
		this.dataset = System.getenv().getOrDefault("FUSEKI_DATASET", "servelogic");

		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						System.getenv().getOrDefault("FUSEKI_USER", "admin"),
						System.getenv().getOrDefault("FUSEKI_PASSWORD", "admin").toCharArray()
						);
			}
		};

		this.httpClient = HttpClient
				.newBuilder()
				.authenticator(auth)
				.build();
	}

	@Bean
	public RDFConnection getConnection() {
		if (connection == null) {
			this.connection = RDFConnectionRemote
					.newBuilder()
					.destination("http://%s:%d/%s".formatted(this.host, this.port, this.dataset))
					.httpClient(httpClient)
					.queryEndpoint("sparql")
					.updateEndpoint("update")
					.gspEndpoint("data")
					.build();
		}
		return this.connection;
	}
	
	@PostConstruct
	public void init() {

        try {
//        	Create the dataset if one doesn't exist
        	String adminUrl = "http://%s:%d/$/datasets".formatted(this.getHost(), this.getPort());
    		String formData = "dbName=%s&dbType=tdb2".formatted(this.getDataset());
    		
    		HttpRequest request = HttpRequest.newBuilder()
    	            .uri(URI.create(adminUrl))
    	            .header("Content-Type", "application/x-www-form-urlencoded")
    	            .POST(HttpRequest.BodyPublishers.ofString(formData))
    	            .build();
			this.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		
        } catch (Exception e) {
			System.err.println("Fuseki initialization failed");
			e.printStackTrace();
		}
    }
	
    public String getHost() {
    	return host;
    }
    
    public int getPort() {
    	return port;
    }
    
    public String getDataset() {
    	return dataset;
    }
    
    public HttpClient getHttpClient() {
		return httpClient;
	}
    
    public String generateUUID4() {
//    	Add a char or two in front so XML standard is satisfied
//    	UUID4 has a chance to generate with a number at the start
//    	This resolves that and leaves the random aspect there still
    	return "sl" + UUID.randomUUID().toString();
    }

}
