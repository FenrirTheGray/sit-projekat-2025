package rs.ac.singidunum.servelogic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.utility.CachedValue;
import rs.ac.singidunum.servelogic.utility.FusekiDBUtility;

@Repository
public class ModifierTypeRepository implements CrudRepository<ModifierType, String> {

	@Autowired
	private FusekiDBUtility db;
	
//	For caching (memoization)
	private final Map<String, CachedValue<ModifierType>> cache = new ConcurrentHashMap<>();
	@Value("${app.cache.ttl:600000}")	//default 10min
	private long TTL_MS;

	@Override
    public List<ModifierType> findAll() {
        String query = String.format(
            "PREFIX : <%s> " +
            "SELECT ?id ?name ?active WHERE { " +
            "  ?uri a :ModifierType ; :name ?name ; :active ?active . " +
            "  BIND(REPLACE(STR(?uri), '^.*(#|/)', '') AS ?id) " +
            "}", ModifierType.ns);
        return executeQueryAndMap(query);
    }

    public List<ModifierType> findAllActive() {
        String query = String.format(
            "PREFIX : <%s> " +
            "SELECT ?id ?name ?active WHERE { " +
            "  ?uri a :ModifierType ; :name ?name ; :active ?active . " +
            "  FILTER(?active = true) " +
            "  BIND(REPLACE(STR(?uri), '^.*(#|/)', '') AS ?id) " +
            "}", ModifierType.ns);
        return executeQueryAndMap(query);
    }
	
	@Override
    public Optional<ModifierType> findById(String id) {
        CachedValue<ModifierType> c = cache.get(id);
        if (c != null && !c.isExpired()) return Optional.of(c.data());

        String query = String.format(
            "PREFIX : <%s> " +
            "SELECT ?name ?active WHERE { :%s a :ModifierType ; :name ?name ; :active ?active . }",
            ModifierType.ns, id);

        try (QueryExecution qexec = db.getConnection().query(query)) {
            ResultSet rs = qexec.execSelect();
            if (rs.hasNext()) {
                QuerySolution sol = rs.nextSolution();
                ModifierType mt = new ModifierType(id, sol.get("name").asLiteral().getString(), sol.get("active").asLiteral().getBoolean());
                cache.put(id, new CachedValue<>(mt, System.currentTimeMillis() + TTL_MS));
                return Optional.of(mt);
            }
        }
        return Optional.empty();
    }
	
	public Optional<ModifierType> findByIdActive(String id) {
		return findById(id).filter(ModifierType::isActive);
	}
	
	@Override
    public <S extends ModifierType> S save(S item) {
        if (item.getId() == null) {
            item.setId(db.generateUUID4());
        } else {
            cache.remove(item.getId());
        }

        String updateQuery = String.format(
            "PREFIX : <%s> " +
            "DELETE { :%s ?p ?o } WHERE { :%s ?p ?o } ; " +
            "INSERT DATA { :%s a :ModifierType ; :name \"%s\" ; :active %b . }",
            ModifierType.ns, item.getId(), item.getId(), item.getId(), item.getName().replace("\"", "\\\""), item.isActive()
        );

        db.getConnection().update(updateQuery);
        cache.put(item.getId(), new CachedValue<>(item, System.currentTimeMillis() + TTL_MS));
        return item;
    }
	
	@Override
    public void deleteById(String id) {
        cache.remove(id);
        db.getConnection().update(String.format("PREFIX : <%s> DELETE WHERE { :%s ?p ?o }", ModifierType.ns, id));
    }
	
	@Override
	public <S extends ModifierType> Iterable<S> saveAll(Iterable<S> entities) {
		List<S> saved = new ArrayList<>();
		entities.forEach(entity -> {
			saved.add(save(entity));
		});
		return saved;
	}

	@Override
    public boolean existsById(String id) {
        if (cache.containsKey(id) && !cache.get(id).isExpired()) return true;
        return db.executeAsk(String.format("PREFIX : <%s> ASK { :%s a :ModifierType }", ModifierType.ns, id));
    }

	@Override
	public Iterable<ModifierType> findAllById(Iterable<String> ids) {
		return findAll().stream().filter(entity -> {
			for (String id: ids) {
				if (id == entity.getId()) {
					return true;
				}
			}
			return false;
		}).toList();
	}

	@Override
	public long count() {
	    String query = String.format("PREFIX : <%s> SELECT (COUNT(?s) AS ?count) WHERE { ?s a :ModifierType }", ModifierType.ns);
	    try (QueryExecution qexec = db.getConnection().query(query)) {
	        ResultSet rs = qexec.execSelect();
	        if (rs.hasNext()) {
	            return rs.next().get("count").asLiteral().getLong(); // Use 'rs' here
	        }
	    }
	    return 0;
	}

	@Override
	public void delete(ModifierType entity) {
		if (entity == null || entity.getId() == null) return;
		deleteById(entity.getId());
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		ids.forEach(id -> {
			deleteById(id);
		});
	}

	@Override
	public void deleteAll(Iterable<? extends ModifierType> entities) {
		entities.forEach(entity -> {
			delete(entity);
		});
	}

	@Override
	public void deleteAll() {
		findAll().forEach(entity -> {
			delete(entity);
		});
	}
	
	private List<ModifierType> executeQueryAndMap(String sparql) {
        List<ModifierType> list = new ArrayList<>();
        try (QueryExecution qexec = db.getConnection().query(sparql)) {
            ResultSet rs = qexec.execSelect();
            while (rs.hasNext()) {
                QuerySolution sol = rs.nextSolution();
                list.add(new ModifierType(
                    sol.get("id").toString(),
                    sol.get("name").asLiteral().getString(),
                    sol.get("active").asLiteral().getBoolean()
                ));
            }
        }
        return list;
    }
	
}
