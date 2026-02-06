package rs.ac.singidunum.servelogic.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.vocabulary.RDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import jakarta.annotation.PostConstruct;
import rs.ac.singidunum.servelogic.dto.create.CategoryCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.file.CategoryXMLWrapper;
import rs.ac.singidunum.servelogic.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.CategoryUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.ICategoryMapper;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.repository.CategoryRepository;

@Service
public class CategoryService extends AbstractService<Category, CategoryResponseDTO, CategoryCreateRequestDTO, CategoryUpdateRequestDTO, CategoryResponseDTO, CategoryXMLWrapper> {
	
	@Autowired
	private CategoryRepository repo;
	@Autowired
	private ICategoryMapper mapper;
	
	private Model schemaModel;
	
	@Override
	public List<CategoryResponseDTO> findAll() {
		
		return this.findAllInit()
				.stream()
				.map(item -> mapper.toResponse(item))
				.collect(Collectors.toList());
		
	}
	
	@Override
	public Optional<CategoryResponseDTO> findById(String id) {
		
		Optional<Category> item = repo.findById(id);
		if (item.isPresent()) {
			return Optional.of(mapper.toResponse(item.get()));
		}
		return Optional.empty();
		
	}
	@Override
	public Optional<CategoryResponseDTO> create(CategoryCreateRequestDTO item) {
		
		Category entity = mapper.createToEntity(item);
		return Optional.ofNullable(mapper.toResponse(repo.save(entity)));
		
	}
	@Override
	public Optional<CategoryResponseDTO> update(CategoryUpdateRequestDTO item) {
		
		Optional<CategoryResponseDTO> updateItem = findById(item.getId());
		if (updateItem.isEmpty()) {
			return Optional.empty();
		}
		if (item.getName() == null) {
			item.setName(updateItem.get().getName());
		}
		if (item.getDescription() == null) {
			item.setDescription(updateItem.get().getDescription());
		}
		if (item.isActive() == null) {
			item.setActive(updateItem.get().isActive());
		}
		Category entity = mapper.updateToEntity(item);
		
		return Optional.ofNullable(mapper.toResponse(repo.save(entity)));
		
	}
	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}
	
	public List<Category> findAllInit() {
		
		return repo.findAll()
				.stream()
				.collect(Collectors.toList());
		
	}

	@Override
	public List<CategoryResponseDTO> findAllExport() {
		return findAll();
	}

	@Override
	protected void processEntities(List<CategoryResponseDTO> dtos) {
		List<Category> itemsToSave = new ArrayList<>();

        for (CategoryResponseDTO dto : dtos) {
            Category item;
            if (StringUtils.hasText(dto.getId())) {
            	item = repo.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Update failed: Id " + dto.getId() + " not found."));
            } else {
            	item = new Category();
            }

            mapper.updateCategoryFromDto(dto, item);
            
            if (StringUtils.hasText(dto.getId())) {
                item.setId(dto.getId());
            }
            itemsToSave.add(item);
        }
        repo.saveAll(itemsToSave);
	}
	
	@Override
    protected Class<CategoryResponseDTO> getDtoClass() {
        return CategoryResponseDTO.class;
    }

    @Override
    protected Class<CategoryXMLWrapper> getXmlWrapperClass() {
        return CategoryXMLWrapper.class;
    }

	@Override
	public CategoryXMLWrapper wrapper(List<CategoryResponseDTO> data) {
		return new CategoryXMLWrapper(data);
	}
	
	@Override
	protected byte[] convertDtosToRdf(List<CategoryResponseDTO> dtos) {
	    Model model = ModelFactory.createDefaultModel();
	    model.setNsPrefix("servelogic", Category.ns);

	    for (CategoryResponseDTO dto : dtos) {
	        // If ID is missing, we create a blank node resource
	        Resource res = (dto.getId() != null) 
	                       ? model.createResource(Category.ns + dto.getId()) 
	                       : model.createResource(); 

	        res.addProperty(RDF.type, model.createResource(Category.ns + "Category"))
	           .addProperty(model.createProperty(Category.ns + "name"), dto.getName())
	           .addProperty(model.createProperty(Category.ns + "description"), dto.getDescription())
	           .addLiteral(model.createProperty(Category.ns + "active"), dto.isActive());
	    }

	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    model.write(out, "RDF/XML");
	    return out.toByteArray();
	}

	@Override
	protected List<CategoryResponseDTO> convertRdfToDtos(byte[] fileData) throws Exception {
		Model dataModel = ModelFactory.createDefaultModel();
	    dataModel.read(new ByteArrayInputStream(fileData), null, "RDF/XML");

	    // Strict Property + Type Check
	    Set<String> allowedProperties = Set.of(
	        RDF.type.getURI(),
	        Category.ns + "name",
	        Category.ns + "description",
	        Category.ns + "active"
	    );
	    String allowedType = Category.ns + "Category";

	    StmtIterator stmtIter = dataModel.listStatements();
	    while (stmtIter.hasNext()) {
	        Statement stmt = stmtIter.nextStatement();
	        String pred = stmt.getPredicate().getURI();
	        
	        // Check for unknown properties
	        if (!allowedProperties.contains(pred)) {
	            throw new RuntimeException("Strict Validation Fail: Unknown property <" + pred + ">");
	        }
	        
	        // Check for unknown types
	        if (pred.equals(RDF.type.getURI())) {
	            String objectType = stmt.getResource().getURI();
	            if (!allowedType.equals(objectType)) {
	                throw new RuntimeException("Strict Validation Fail: Invalid type <" + objectType + ">. Only sl:Category is allowed.");
	            }
	        }
	    }

	    // RDFS Inference Validation (Datatypes/Range/Domain)
	    // Schema in resources/schemas/category-schema'schemaModel'
	    InfModel infModel = ModelFactory.createRDFSModel(schemaModel, dataModel);
	    ValidityReport report = infModel.validate();
	    if (!report.isValid()) {
	        throw new RuntimeException("RDF Logic Error: " + report.getReports().next().toString());
	    }

//	    To dto
	    List<CategoryResponseDTO> dtos = new ArrayList<>();
	    
	    Property pName = dataModel.createProperty(Category.ns + "name");
	    Property pDesc = dataModel.createProperty(Category.ns + "description");
	    Property pActive = dataModel.createProperty(Category.ns + "active");

	    ResIterator iter = dataModel.listResourcesWithProperty(RDF.type, dataModel.getResource(Category.ns + "Category"));

	    while (iter.hasNext()) {
	        Resource res = iter.nextResource();
	        CategoryResponseDTO dto = new CategoryResponseDTO();

	        // ID Logic: Get the fragment after the #
	        if (res.isURIResource()) {
	            String uri = res.getURI();
	            if (uri.contains("#") && !uri.endsWith("#")) {
	                dto.setId(uri.substring(uri.indexOf("#") + 1));
	            } else {
	                dto.setId(null);
	            }
	        } else if (res.isAnon()) {
	            dto.setId(null);
	        }

	        if (res.hasProperty(pName)) dto.setName(res.getProperty(pName).getString());
	        if (res.hasProperty(pDesc)) dto.setDescription(res.getProperty(pDesc).getString());
	        if (res.hasProperty(pActive)) dto.setActive(res.getProperty(pActive).getBoolean());

	        dtos.add(dto);
	    }
	    return dtos;
	}

	@PostConstruct
	public void init() {
	    Model baseSchema = ModelFactory.createDefaultModel();
	    
	    // Load the RDFS file from your resources
	    try (InputStream in = getClass().getResourceAsStream("/schemas/category-schema.rdfs")) {
	        if (in == null) {
	            throw new RuntimeException("RDFS Schema file not found at /schemas/category-schema.rdfs");
	        }
	        
	        baseSchema.read(in, null, "RDF/XML");
	        
	        this.schemaModel = baseSchema;
	        
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to load RDFS schema", e);
	    }
	}
}
