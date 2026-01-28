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
		
		List<ModifierType> list = new ArrayList<ModifierType>();
		Model model = db.getConnection().fetch();
		Resource classModifierType = model.createResource("%sModifierType".formatted(ModifierType.ns));

		ResIterator resources = model.listResourcesWithProperty(RDF.type, classModifierType);
		while (resources.hasNext()) {
			list.add(this.objectFromTriplet(resources.nextResource()));
		}
		return list;
		
	}
	
	public List<ModifierType> findAllActive() {
		
		List<ModifierType> list = new ArrayList<ModifierType>();
		Model model = db.getConnection().fetch();
		Resource classModifierType = model.createResource("%sModifierType".formatted(ModifierType.ns));

		ResIterator resources = model.listResourcesWithProperty(RDF.type, classModifierType);
		while (resources.hasNext()) {
			ModifierType item = this.objectFromTriplet(resources.nextResource());
			if (item.isActive()) {
				list.add(item);
			}
		}
		return list;
		
	}
	
	@Override
	public Optional<ModifierType> findById(String id) {
		
		CachedValue<ModifierType> c = cache.get(id);
		if (c == null || c.isExpired()) {
			if (c != null) cache.remove(id);
		
			Model model = db.getConnection().fetch();
	
			String url = "%s%s".formatted(ModifierType.ns, id);
			Resource resource = model.getResource(url);
			
			ModifierType item = this.objectFromTriplet(resource);
			if (item == null) {
				return Optional.empty();
			}
			cache.put(id, new CachedValue<ModifierType>(item, System.currentTimeMillis() + TTL_MS));
			return Optional.of(item);
		
		}
		
		return Optional.of(c.data());
		
	}
	
	public Optional<ModifierType> findByIdActive(String id) {
		Optional<ModifierType> item = findById(id);
		if (item.isEmpty() || !item.get().isActive()) {
			return Optional.empty();
		}
		return item;
		
	}
	
	@Override
	public <S extends ModifierType> S save(S item) {
		
		if (item.getId() == null) {
			item.setId(db.generateUUID4());
		} else {
			CachedValue<ModifierType> c = cache.get(item.getId());
			if (c != null) {
				cache.remove(item.getId());
			}
		}
		Model model = db.getConnection().fetch();
		
		Resource resource = model.createResource(item.getUrl());

		Property nameProp = model.createProperty("%sname".formatted(ModifierType.ns));
		Property activeProp = model.createProperty("%sactive".formatted(ModifierType.ns));
		Resource typeClass = model.createResource("%sModifierType".formatted(ModifierType.ns));

//		This removes every triple where this resource is the subject
//	    Allows us to have update and save in the same method
		model.removeAll(resource, null, null);

		resource.addProperty(RDF.type, typeClass);
		resource.addProperty(nameProp, item.getName());
		resource.addLiteral(activeProp, item.isActive());

		db.getConnection().put(model);

		return item;
		
	}
	
	@Override
	public void deleteById(String id) {
		
		CachedValue<ModifierType> c = cache.get(id);
		if (c != null) {
			cache.remove(id);
		}

		Model model = db.getConnection().fetch();
		
		Resource resource = model.getResource("%s%s".formatted(ModifierType.ns, id));

		model.removeAll(resource, null, null);

		db.getConnection().put(model);

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
		Optional<ModifierType> item = findById(id);
		if (item.isPresent()) return true;
		return false;
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
		return findAll().size();
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
	
	private ModifierType objectFromTriplet(Resource resource) {
		
		String id = resource.getLocalName();
		String name = null;
		boolean active = false;

		StmtIterator props = resource.listProperties();
		while (props.hasNext()) {
			Statement prop = props.nextStatement();
			Property predicate = prop.getPredicate();
			RDFNode object = prop.getObject();

			if (predicate.getURI().equals("%sname".formatted(ModifierType.ns))) {
				name = object.asLiteral().getString();
			} else if (predicate.getURI().equals("%sactive".formatted(ModifierType.ns))) {
				active = object.asLiteral().getBoolean();
			}

		}
		if (name == null) {
			return null;
		}
		return new ModifierType(id, name, active);
		
	}
	
}
