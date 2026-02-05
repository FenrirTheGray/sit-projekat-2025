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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class FusekiDBUtility {

	@Value("${app.fuseki.host}")
	private String host;

	@Value("${app.fuseki.port}")
	private int port;

	@Value("${app.fuseki.dataset}")
	private String dataset;

	@Value("${app.fuseki.user}")
	private String fusekiUser;

	@Value("${app.fuseki.password}")
	private String fusekiPassword;

	private HttpClient httpClient;
	private RDFConnection connection;

	@PostConstruct
	public void initHttpClient() {
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fusekiUser, fusekiPassword.toCharArray());
			}
		};

		this.httpClient = HttpClient
				.newBuilder()
				.authenticator(auth)
				.build();

		initDataset();
	}

	private void initDataset() {
		try {
			String adminUrl = "http://%s:%d/$/datasets".formatted(this.host, this.port);
			String formData = "dbName=%s&dbType=tdb2".formatted(this.dataset);

			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(adminUrl))
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(HttpRequest.BodyPublishers.ofString(formData))
					.build();
			this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		} catch (Exception e) {
			System.err.println("Fuseki initialization failed");
			e.printStackTrace();
		}
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
		return "sl" + UUID.randomUUID().toString();
	}

}
