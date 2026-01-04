package rs.ac.singidunum.servelogic.utility;

import org.springframework.context.annotation.Configuration;
import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableArangoRepositories(basePackages = { "rs.ac.singidunum.servelogic.repository" })
public class ArangoDBUtility implements ArangoConfiguration {

	@Override
	public ArangoDB.Builder arango() {
		String host = System.getenv().getOrDefault("ARANGODB_HOST", "localhost");
		int port = Integer.parseInt(System.getenv().getOrDefault("ARANGODB_PORT", "8529"));
		String user = System.getenv().getOrDefault("ARANGODB_USER", "root");
		String password = System.getenv().getOrDefault("ARANGODB_PASSWORD", "root");

		return new ArangoDB.Builder()
				.host(host, port)
				.user(user)
				.password(password);
	}

	@Override
	public String database() {
		String database = System.getenv().getOrDefault("ARANGODB_DATABASE", "servelogic");
		return database;
	}

	@PostConstruct
	public void init() {
		ArangoDB arangoDB = arango().build();
		try {
			if (!arangoDB.db(database()).exists()) {
				arangoDB.createDatabase(database());
			}
		} catch (Exception e) {
			throw new RuntimeException("Cannot create or access database", e);
		}
	}

}
