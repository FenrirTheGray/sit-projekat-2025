package rs.ac.singidunum.servelogic.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.utility.FusekiDBUtility;

@Repository
public class CategoryRepository {
	
	@Autowired
	private FusekiDBUtility db;
	
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
	
	public Optional<Category> findById(String id) {
		
		Model model = db.getConnection().fetch();
		
		String url = "%s%s".formatted(Category.ns, id);
		Resource resource = model.getResource(url);
		
		Category item = this.objectFromTriplet(resource);
		return Optional.ofNullable(item);
		
	}
	
	public Optional<Category> findByIdActive(String id) {
		
		Model model = db.getConnection().fetch();
		
		String url = "%s%s".formatted(Category.ns, id);
		Resource resource = model.getResource(url);
		
		Category item = this.objectFromTriplet(resource);
		if (item == null || !item.isActive()) {
			return Optional.empty();
		}
		return Optional.of(item);
		
	}
	
	public Category save(Category item) {
		
		Model model = db.getConnection().fetch();
		
		if (item.getId() == null) {
			item.setId(db.generateUUID4());
		}
		
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
	
	public void deleteById(String id) {
		
		Model model = db.getConnection().fetch();
		Resource resource = model.getResource("%s%s".formatted(Category.ns, id));
		
		model.removeAll(resource, null, null);

		db.getConnection().put(model);
		
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
