package rs.ac.singidunum.servelogic.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;
import jakarta.annotation.PostConstruct;

@Configuration
@EnableArangoRepositories(basePackages = { "rs.ac.singidunum.servelogic.repository" })
public class ArangoDBUtility implements ArangoConfiguration {

	@Value("${app.arangodb.host}")
	private String host;

	@Value("${app.arangodb.port}")
	private int port;

	@Value("${app.arangodb.user}")
	private String user;

	@Value("${app.arangodb.password}")
	private String password;

	@Value("${app.arangodb.database}")
	private String databaseName;

	@Override
	public ArangoDB.Builder arango() {
		return new ArangoDB.Builder()
				.host(host, port)
				.user(user)
				.password(password);
	}

	@Override
	public String database() {
		return databaseName;
	}

	@PostConstruct
	public void init() {
		ArangoDB arangoDB = arango().build();
		try {
			if (!arangoDB.db(database()).exists()) {
				arangoDB.createDatabase(database());
			}
			var db = arangoDB.db(database());
			if (!db.collection("password_reset_token").exists()) {
				db.createCollection("password_reset_token");
			}
		} catch (Exception e) {
			throw new RuntimeException("Cannot create or access database", e);
		}
	}

}
