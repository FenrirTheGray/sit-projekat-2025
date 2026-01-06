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
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.servelogic.model.ModificationType;
import rs.ac.singidunum.servelogic.utility.FusekiDBUtility;

@Repository
public class ModificationTypeRepository {

	private final FusekiDBUtility db;

	public ModificationTypeRepository(FusekiDBUtility db) {
		super();
		this.db = db;
	}

	public List<ModificationType> findAll() {

		List<ModificationType> list = new ArrayList<ModificationType>();
		Model model = db.getConnection().fetch();
		Resource classModificationType = model.createResource("%sModificationType".formatted(ModificationType.ns));

		ResIterator resources = model.listResourcesWithProperty(RDF.type, classModificationType);
		while (resources.hasNext()) {
			list.add(this.objectFromTriplet(resources.nextResource()));
		}

		return list;

	}
	
	public List<ModificationType> findAllActive() {

		List<ModificationType> list = new ArrayList<ModificationType>();
		Model model = db.getConnection().fetch();
		Resource classModificationType = model.createResource("%sModificationType".formatted(ModificationType.ns));

		ResIterator resources = model.listResourcesWithProperty(RDF.type, classModificationType);
		while (resources.hasNext()) {
			ModificationType item = this.objectFromTriplet(resources.nextResource());
			if (item.isActive()) {
				list.add(item);
			}
		}

		return list;

	}

	public Optional<ModificationType> findById(String id) {

		Model model = db.getConnection().fetch();

		String url = "%s%s".formatted(ModificationType.ns, id);
		Resource resource = model.getResource(url);
		
		ModificationType item = this.objectFromTriplet(resource);
		if (item == null) {
			return Optional.empty();
		}
		return Optional.of(item);
	}
	
	public Optional<ModificationType> findByIdActive(String id) {
		Model model = db.getConnection().fetch();

		String url = "%s%s".formatted(ModificationType.ns, id);
		Resource resource = model.getResource(url);
		
		ModificationType item = this.objectFromTriplet(resource);
		if (item == null || !item.isActive()) {
			return Optional.empty();
		}
		return Optional.of(item);
	}

	public Optional<ModificationType> save(ModificationType item) {

		Model model = db.getConnection().fetch();

		if (item.getId() == null) {
			item.setId(db.generateUUID4());
		}
		
		Resource resource = model.createResource(item.getUrl());

		Property nameProp = model.createProperty("%sname".formatted(ModificationType.ns));
		Property activeProp = model.createProperty("%sactive".formatted(ModificationType.ns));
		Resource typeClass = model.createResource("%sModificationType".formatted(ModificationType.ns));

//		This removes every triple where this resource is the subject
//	    Allows us to have update and save in the same method
		model.removeAll(resource, null, null);

		resource.addProperty(RDF.type, typeClass);
		resource.addProperty(nameProp, item.getName());
		resource.addLiteral(activeProp, item.isActive());

		db.getConnection().put(model);

		return Optional.of(item);
	}

	public void deleteById(String id) {

		Model model = db.getConnection().fetch();
		Resource resource = model.getResource("%s%s".formatted(ModificationType.ns, id));

		model.removeAll(resource, null, null);

		db.getConnection().put(model);

	}
	
	private ModificationType objectFromTriplet(Resource resource) {
		String id = resource.getLocalName();
		String name = null;
		boolean active = false;

		StmtIterator props = resource.listProperties();
		while (props.hasNext()) {
			Statement prop = props.nextStatement();
			Property predicate = prop.getPredicate();
			RDFNode object = prop.getObject();

			if (predicate.getURI().equals("%sname".formatted(ModificationType.ns))) {
				name = object.asLiteral().getString();
			} else if (predicate.getURI().equals("%sactive".formatted(ModificationType.ns))) {
				active = object.asLiteral().getBoolean();
			}

		}
		if (name == null) {
			return null;
		}
		return new ModificationType(id, name, active);
	}
}
