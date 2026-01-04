package rs.ac.singidunum.servelogic.utility;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.function.Consumer;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;

public class FusekiDBUtility {
	private final String host;
	private final int port;
	private final String dataset;
	private final HttpClient httpClient;

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
		
		this.init();
	}

	private RDFConnection getConnection() {
		return RDFConnectionRemote
				.newBuilder()
				.destination("http://%s:%d/%s".formatted(this.host, this.port, this.dataset))
				.httpClient(httpClient)
				.queryEndpoint("sparql")
				.updateEndpoint("update")
				.gspEndpoint("data")
				.build();
	}
	
	private void init() {
		String adminUrl = "http://%s:%d/$/datasets".formatted(this.host, this.port);
		String formData = "dbName=%s&dbType=tdb2".formatted(this.dataset);
		
		HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(adminUrl))
	            .header("Content-Type", "application/x-www-form-urlencoded")
	            .POST(HttpRequest.BodyPublishers.ofString(formData))
	            .build();

	    try {
	        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
	        
	        if (response.statusCode() == 200 || response.statusCode() == 201) {
	            System.out.println("Dataset created successfully.");
	        } else if (response.statusCode() == 409) {
	            System.out.println("Dataset already exists. Skipping.");
	        } else {
	            System.err.println("Failed to create dataset. Status: " + response.statusCode());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void select(String queryString, Consumer<QuerySolution> rowProcessor) {
        try (RDFConnection conn = getConnection()) {
            conn.querySelect(queryString, rowProcessor);
        }
    }

    public void update(String updateString) {
        try (RDFConnection conn = getConnection()) {
            conn.update(updateString);
        }
    }

    public void loadFile(String filePath) {
        try (RDFConnection conn = getConnection()) {
            conn.load(filePath);
        }
    }

}
