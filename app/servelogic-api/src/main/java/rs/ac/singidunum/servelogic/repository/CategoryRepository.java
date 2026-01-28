package rs.ac.singidunum.servelogic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;
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

		List<Category> list = new ArrayList<Category>();
		Model model = db.getConnection().fetch();
		Resource classCategory = model.createResource("%sCategory".formatted(Category.ns));

		ResIterator resources = model.listResourcesWithProperty(RDF.type, classCategory);
		while (resources.hasNext()) {
			list.add(this.objectFromTriplet(resources.nextResource()));
		}
		return list;

	}

	public List<Category> findAllActive() {

		List<Category> list = new ArrayList<Category>();
		Model model = db.getConnection().fetch();
		Resource classCategory = model.createResource("%sCategory".formatted(Category.ns));

		ResIterator resources = model.listResourcesWithProperty(RDF.type, classCategory);
		while (resources.hasNext()) {
			Category item = this.objectFromTriplet(resources.nextResource());
			if (item.isActive()) {
				list.add(item);
			}
		}
		return list;

	}

	@Override
	public Optional<Category> findById(String id) {

		CachedValue<Category> c = cache.get(id);
		if (c == null || c.isExpired()) {
			if (c != null) cache.remove(id);
			
			Model model = db.getConnection().fetch();

			String url = "%s%s".formatted(Category.ns, id);
			Resource resource = model.getResource(url);

			Category item = this.objectFromTriplet(resource);
			if (item == null) {
				return Optional.empty();
			}
			cache.put(id, new CachedValue<Category>(item, System.currentTimeMillis() + TTL_MS));
			return Optional.of(item);
		}
		
		return Optional.of(c.data());

			

	}

	public Optional<Category> findByIdActive(String id) {

		Optional<Category> item = findById(id);
		if (item.isEmpty() || !item.get().isActive()) {
			return Optional.empty();
		}
		return item;

	}

	@Override
	public <S extends Category> S save(S item) {

		if (item.getId() == null) {
			item.setId(db.generateUUID4());
		} else {
			CachedValue<Category> c = cache.get(item.getId());
			if (c != null) {
				cache.remove(item.getId());
			}
		}
		
		Model model = db.getConnection().fetch();

		Resource resource = model.createResource(item.getUrl());

		Property nameProp = model.createProperty("%sname".formatted(Category.ns));
		Property descriptionProp = model.createProperty("%sdescription".formatted(Category.ns));
		Property activeProp = model.createProperty("%sactive".formatted(Category.ns));
		Resource typeClass = model.createProperty("%sCategory".formatted(Category.ns));

		model.removeAll(resource, null, null);

		resource.addProperty(RDF.type, typeClass);
		resource.addProperty(nameProp, item.getName());
		resource.addProperty(descriptionProp, item.getDescription());
		resource.addLiteral(activeProp, item.isActive());

		db.getConnection().put(model);

		return item;

	}

	@Override
	public void deleteById(String id) {
		
		CachedValue<Category> c = cache.get(id);
		if (c != null) {
			cache.remove(id);
		}

		Model model = db.getConnection().fetch();
		
		Resource resource = model.getResource("%s%s".formatted(Category.ns, id));

		model.removeAll(resource, null, null);

		db.getConnection().put(model);

	}

	@Override
	public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
		List<S> saved = new ArrayList<>();
		entities.forEach(entity -> {
			saved.add(save(entity));
		});
		return saved;
	}

	@Override
	public boolean existsById(String id) {
		Optional<Category> item = findById(id);
		if (item.isPresent()) return true;
		return false;
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
		return findAll().size();
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
	
	private Category objectFromTriplet(Resource resource) {

		String id = resource.getLocalName();
		String name = null;
		String description = null;
		boolean active = false;

		StmtIterator props = resource.listProperties();
		while (props.hasNext()) {
			Statement prop = props.nextStatement();
			Property predicate = prop.getPredicate();
			RDFNode object = prop.getObject();

			if (predicate.getURI().equals("%sname".formatted(Category.ns))) {
				name = object.asLiteral().getString();
			} else if (predicate.getURI().equals("%sdescription".formatted(Category.ns))) {
				description = object.asLiteral().getString();
			} else if (predicate.getURI().equals("%sactive".formatted(Category.ns))) {
				active = object.asLiteral().getBoolean();
			}

		}
		if (name == null) {
			return null;
		}
		return new Category(id, name, description, active);

	}

}
