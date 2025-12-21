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
//		TODO: move connection configuration to a configuration file
		return new ArangoDB.Builder()
				.host("localhost", 8529)
				.user("root")
				.password("root");
	}

	@Override
	public String database() {
		return "servelogic";
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
