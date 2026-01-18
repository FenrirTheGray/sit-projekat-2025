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
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.utility.FusekiDBUtility;

@Repository
public class ModifierTypeRepository {

	@Autowired
	private FusekiDBUtility db;

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
	
	public Optional<ModifierType> findById(String id) {
		
		Model model = db.getConnection().fetch();

		String url = "%s%s".formatted(ModifierType.ns, id);
		Resource resource = model.getResource(url);
		
		ModifierType item = this.objectFromTriplet(resource);
		return Optional.ofNullable(item);
		
	}
	
	public Optional<ModifierType> findByIdActive(String id) {
		
		Model model = db.getConnection().fetch();

		String url = "%s%s".formatted(ModifierType.ns, id);
		Resource resource = model.getResource(url);
		
		ModifierType item = this.objectFromTriplet(resource);
		if (item == null || !item.isActive()) {
			return Optional.empty();
		}
		return Optional.of(item);
		
	}
	
	public ModifierType save(ModifierType item) {
		
		Model model = db.getConnection().fetch();

		if (item.getId() == null) {
			item.setId(db.generateUUID4());
		}
		
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
	
	public void deleteById(String id) {

		Model model = db.getConnection().fetch();
		Resource resource = model.getResource("%s%s".formatted(ModifierType.ns, id));

		model.removeAll(resource, null, null);

		db.getConnection().put(model);

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
