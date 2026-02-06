package rs.ac.singidunum.servelogic.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.utility.CachedValue;
import rs.ac.singidunum.servelogic.utility.FusekiDBUtility;

@Repository
public class CategoryRepository implements CrudRepository<Category, String>{

	@Autowired
	private FusekiDBUtility db;

//	For caching (memoization)
	private final Map<String, CachedValue<Category>> cache = new ConcurrentHashMap<>();
	@Value("${app.cache.ttl:600000}")	//default 10min
	private long TTL_MS;

	@Override
	public List<Category> findAll() {
	    String query = String.format(
	        "PREFIX : <%s> " +
	        "SELECT ?id ?name ?description ?active WHERE { " +
	        "  ?uri a :Category ; " +
	        "       :name ?name ; " +
	        "       :description ?description ; " +
	        "       :active ?active . " +
	        "  BIND(REPLACE(STR(?uri), '^.*(#|/)', '') AS ?id) " +
	        "}", Category.ns);

	    return executeQueryAndMap(query);
	}

	public List<Category> findAllActive() {
	    String query = String.format(
	        "PREFIX : <%s> " +
	        "SELECT ?id ?name ?description ?active WHERE { " +
	        "  ?uri a :Category ; " +
	        "       :name ?name ; " +
	        "       :description ?description ; " +
	        "       :active ?active . " +
	        "  FILTER(?active = true) " +
	        "  BIND(REPLACE(STR(?uri), '^.*(#|/)', '') AS ?id) " +
	        "}", Category.ns);

	    return executeQueryAndMap(query);
	}

	@Override
    public Optional<Category> findById(String id) {
        CachedValue<Category> c = cache.get(id);
        if (c != null && !c.isExpired()) return Optional.of(c.data());

        String query = String.format(
            "PREFIX : <%s> " +
            "SELECT ?name ?description ?active WHERE { " +
            "  :%s a :Category ; :name ?name ; :description ?description ; :active ?active . " +
            "}", Category.ns, id);

        try (QueryExecution qexec = db.getConnection().query(query)) {
            ResultSet rs = qexec.execSelect();
            if (rs.hasNext()) {
                QuerySolution sol = rs.nextSolution();
                Category item = new Category(
                    id,
                    sol.get("name").asLiteral().getString(),
                    sol.get("description").asLiteral().getString(),
                    sol.get("active").asLiteral().getBoolean()
                );
                cache.put(id, new CachedValue<>(item, System.currentTimeMillis() + TTL_MS));
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
	
	public Optional<Category> findByIdActive(String id) {
        return findById(id).filter(Category::isActive);
    }

	@Override
	public <S extends Category> S save(S item) {
	    if (item.getId() == null) {
	        item.setId(db.generateUUID4());
	    } else {
	        cache.remove(item.getId());
	    }

	    String updateQuery = String.format(
	        "PREFIX : <%s> " +
	        "DELETE { :%s ?p ?o } " +
	        "WHERE { :%s ?p ?o } ; " +
	        "INSERT DATA { " +
	        "  :%s a :Category ; " +
	        "      :name \"%s\" ; " +
	        "      :description \"%s\" ; " +
	        "      :active %b . " +
	        "}", 
	        Category.ns, item.getId(), item.getId(), 
	        item.getId(), item.getName().replace("\"", "\\\""), item.getDescription(), item.isActive()
	    );

	    db.getConnection().update(updateQuery);
	    
	    cache.put(item.getId(), new CachedValue<>(item, System.currentTimeMillis() + TTL_MS));
	    return item;
	}

	@Override
	public void deleteById(String id) {
	    cache.remove(id);
	    String deleteQuery = String.format(
	        "PREFIX : <%s> DELETE WHERE { :%s ?p ?o }", 
	        Category.ns, id
	    );
	    db.getConnection().update(deleteQuery);
	}

	@Override
	public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
	    StringBuilder sparqlBatch = new StringBuilder();
	    sparqlBatch.append(String.format("PREFIX : <%s> \n", Category.ns));
	    
	    List<S> savedItems = new ArrayList<>();
	    
	    for (S item : entities) {
	        if (item.getId() == null) {
	            item.setId(db.generateUUID4());
	        }
	        
	        cache.remove(item.getId());

	        // Construct one DELETE/INSERT block per entity
	        sparqlBatch.append(String.format(
	            "DELETE { :%s ?p ?o } WHERE { :%s ?p ?o } ;\n" +
	            "INSERT DATA { " +
	            "  :%s a :Category ; " +
	            "      :name \"%s\" ; " +
	            "      :description \"%s\" ; " +
	            "      :active %b . " +
	            "} ;\n", 
	            item.getId(), item.getId(), 
	            item.getId(), 
	            item.getName().replace("\"", "\\\""), 
	            item.getDescription() != null ? item.getDescription().replace("\"", "\\\"") : "", 
	            item.isActive()
	        ));
	        savedItems.add(item);
	    }

	    db.getConnection().update(sparqlBatch.toString());
	    
	    for (S item : savedItems) {
	        cache.put(item.getId(), new CachedValue<>(item, System.currentTimeMillis() + TTL_MS));
	    }
	    
	    return savedItems;
	}

	@Override
	public boolean existsById(String id) {
	    String askQuery = String.format(
	        "PREFIX : <%s> ASK { :%s a :Category }", 
	        Category.ns, id
	    );
	    return db.executeAsk(askQuery);
	}

	@Override
	public Iterable<Category> findAllById(Iterable<String> ids) {
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
        String query = String.format("PREFIX : <%s> SELECT (COUNT(?s) AS ?count) WHERE { ?s a :Category }", Category.ns);
        try (QueryExecution qexec = db.getConnection().query(query)) {
            ResultSet rs = qexec.execSelect();
            if (rs.hasNext()) {
                return rs.next().get("count").asLiteral().getLong();
            }
        }
        return 0;
    }

	@Override
	public void delete(Category entity) {
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
	public void deleteAll(Iterable<? extends Category> entities) {
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
	
	public Set<String> findExistingIds(Set<String> ids) {
	    if (ids == null || ids.isEmpty()) return Collections.emptySet();

	    StringBuilder sparql = new StringBuilder();
	    sparql.append("PREFIX : <").append(Category.ns).append("> \n");
	    sparql.append("SELECT ?id WHERE { \n");
	    sparql.append("  ?uri a :Category . \n");
	    // This regex grabs everything after the last # or /
	    sparql.append("  BIND(REPLACE(STR(?uri), '^.*(#|/)', '') AS ?id) \n");
	    sparql.append("  VALUES ?id { ");
	    for (String id : ids) {
	        sparql.append("\"").append(id).append("\" ");
	    }
	    sparql.append(" } \n}");

	    return db.executeSelect(sparql.toString());
	}
	
	private List<Category> executeQueryAndMap(String sparql) {
	    List<Category> list = new ArrayList<>();
	    try (QueryExecution qexec = db.getConnection().query(sparql)) {
	        ResultSet rs = qexec.execSelect();
	        while (rs.hasNext()) {
	            QuerySolution sol = rs.nextSolution();
	            list.add(new Category(
	                sol.get("id").toString(),
	                sol.get("name").asLiteral().getString(),
	                sol.get("description").asLiteral().getString(),
	                sol.get("active").asLiteral().getBoolean()
	            ));
	        }
	    }
	    return list;
	}

}
