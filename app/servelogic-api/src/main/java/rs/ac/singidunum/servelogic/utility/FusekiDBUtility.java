package rs.ac.singidunum.servelogic.utility;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.http.HttpClient;
import java.util.UUID;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
