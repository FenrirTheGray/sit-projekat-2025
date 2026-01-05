package rs.ac.singidunum.servelogic.utility.devDataInit;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.RDF;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import rs.ac.singidunum.servelogic.model.ModificationType;
import rs.ac.singidunum.servelogic.utility.FusekiDBUtility;

@Component
public class ModificationTypeDataInit {
	
	private final FusekiDBUtility db;
	
	public ModificationTypeDataInit(FusekiDBUtility db) {
		super();
		this.db = db;
	}

	@PostConstruct
	public void init() {

        try {
        	
//        	Create the dataset if one doesn't exist
        	String adminUrl = "http://%s:%d/$/datasets".formatted(db.getHost(), db.getPort());
    		String formData = "dbName=%s&dbType=tdb2".formatted(db.getDataset());
    		
    		HttpRequest request = HttpRequest.newBuilder()
    	            .uri(URI.create(adminUrl))
    	            .header("Content-Type", "application/x-www-form-urlencoded")
    	            .POST(HttpRequest.BodyPublishers.ofString(formData))
    	            .build();
    		
			HttpResponse<String> response = db.getHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	        
	        if (response.statusCode() == 200 || response.statusCode() == 201) {
//	        	System.out.println("Dataset created successfully.");
	        	dataInit();
	        } else if (response.statusCode() == 409) {
//	            System.out.println("Dataset already exists. Skipping.");
	        } else {
	            System.err.println("Failed to create dataset. Status: " + response.statusCode());
	        }
        	
		} catch (Exception e) {
			System.err.println("Fuseki initialization failed");
			e.printStackTrace();
		}
    }
	
	private void dataInit() {
		
		Model model = ModelFactory.createDefaultModel();
    	
//    	Classes
    	Resource classModificationType = model.createResource("%sModificationType".formatted(ModificationType.ns));
    	
//    	Properties
    	Property modificationTypeName = model.createProperty("%sname".formatted(ModificationType.ns));
    	Property modificationTypeActive = model.createProperty("%sactive".formatted(ModificationType.ns));
    	
//    	modificationType1
    	Resource modificationType1 = model.createResource("%smodificationType1".formatted(ModificationType.ns));
    	modificationType1
    		.addProperty(RDF.type, classModificationType)
    		.addProperty(modificationTypeName, "size")
			.addProperty(modificationTypeActive, model.createTypedLiteral(true));
    	
//    	modificationType2
    	Resource modificationType2 = model.createResource("%smodificationType2".formatted(ModificationType.ns));
    	modificationType2
    		.addProperty(RDF.type, classModificationType)
    		.addProperty(modificationTypeName, "topping")
			.addProperty(modificationTypeActive, model.createTypedLiteral(true));
    	
//    	Wrap up
    	db.getConnection().load(model);
    	System.out.println("Data added to dataset successfully.");
	}
}
