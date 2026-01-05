package rs.ac.singidunum.servelogic.repository;

import java.util.ArrayList;
import java.util.List;

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
		while(resources.hasNext()) {
			Resource resource = resources.nextResource();
			String url = resource.getLocalName();
			String name = "";
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
			list.add(new ModificationType(url, name, active));
		}
		
		return list;
		
	}
}
