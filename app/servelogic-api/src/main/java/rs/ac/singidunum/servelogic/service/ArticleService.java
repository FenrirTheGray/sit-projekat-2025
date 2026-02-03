package rs.ac.singidunum.servelogic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import rs.ac.singidunum.servelogic.dto.create.ArticleCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.file.ArticleFileDTO;
import rs.ac.singidunum.servelogic.dto.file.ArticleXMLWrapper;
import rs.ac.singidunum.servelogic.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ArticleUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.ArticleMapper;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.repository.CategoryRepository;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;
import rs.ac.singidunum.servelogic.repository.IModifierRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

@Service
public class ArticleService {
	
	@Autowired
	private IArticleRepository repo;
	@Autowired
	private ArangoFusekiReferenceService populator;
	@Autowired
	ArticleMapper mapper;
    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private IModifierRepository modifierRepo;
    
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final XmlMapper xmlMapper = new XmlMapper();

	public List<ArticleResponseDTO> findAll() {
		
		return populator.populateAllArticles(StreamSupport
				.stream(repo.findAll().spliterator(), false)	// false = not parallel
				.collect(Collectors.toList()))
				.stream()
				.map(item -> mapper.toResponse(item))
				.collect(Collectors.toList());
		
	}

	public List<Article> findAllInit(){
		return populator.populateAllArticles(StreamSupport
				.stream(repo.findAll().spliterator(), false)
				.collect(Collectors.toList()));
	}
	public List<ArticleFileDTO> findAllExport() {
		
		return repo.findAllExportRaw();
		
	}
	
	public Optional<ArticleResponseDTO> findByKey(String key) {
		
		Optional<Article> item = repo.findById(key);
		if (item.isPresent()) {
			return Optional.of(mapper.toResponse(populator.populate(item.get())));
		}
		return Optional.empty();
		
	}
	
	public Optional<ArticleResponseDTO> create(ArticleCreateRequestDTO item) {
		
		Article entity = mapper.createToEntity(item);

		entity = populator.populate(entity);
		if (entity != null && entity.getCategory() != null) {
			return Optional.of(mapper.toResponse(repo.save(entity))); 				
		}
		return Optional.empty();
		
	}
	
	public Optional<ArticleResponseDTO> update(ArticleUpdateRequestDTO item) {
		
		Optional<ArticleResponseDTO> updateItem = findByKey(item.getKey());
		if (updateItem.isEmpty()) {
			return Optional.empty();
		}
		if (item.getName() == null) {
			item.setName(updateItem.get().getName());
		}
		if (item.getDescription() == null) {
			item.setDescription(updateItem.get().getDescription());
		}
		if (item.getImageUrl() == null) {
			item.setImageUrl(updateItem.get().getImageUrl());
		}
		if (item.getBasePrice() == null) {
			item.setBasePrice(updateItem.get().getBasePrice());
		}
		if (item.isActive() == null) {
			item.setActive(updateItem.get().isActive());
		}
		if (item.getCategoryId() == null) {
			item.setCategoryId(updateItem.get().getCategory().getId());
		}
		if (item.getModifiers() == null) {
			item.setModifiers(updateItem.get().getModifiers().stream().map(e -> e.getKey()).toList());
		}
		Article entity = mapper.updateToEntity(item);

		if (entity.getCategoryId() != null) {
			entity.setCategory(mapper.idToCategory(entity.getCategoryId()));
			try {
//				If modifiers aren't proper existing ids, this prevents the server from erroring out and rejects the change instead responding to the client
				return Optional.ofNullable(mapper.toResponse(repo.save(entity)));
			} catch (Exception e) {}
		}
		return Optional.empty();
		
	}
	
	public void deleteByKey(String key) {
		repo.deleteById(key);
	}
	
	@Transactional
	public void importData(byte[] fileData, String format) throws Exception {
		
	    List<ArticleFileDTO> dtos = convertToDtos(fileData, format);
	    processEntities(dtos);
	}
	 
	private void processEntities(List<ArticleFileDTO> dtos) {
        List<Article> articlesToSave = new ArrayList<>();

        for (ArticleFileDTO dto : dtos) {
            Article article;
            if (StringUtils.hasText(dto.getKey())) {
            	article = repo.findById(dto.getKey()).orElseThrow(() -> new RuntimeException("Update failed: Key " + dto.getKey() + " not found."));
            } else {
            	article = new Article();
            }

            verifyReferencesExist(dto);
            mapper.updateArticleFromDto(dto, article);
            
            if (StringUtils.hasText(dto.getKey())) {
                article.setKey(dto.getKey());
            }
            articlesToSave.add(article);
        }
        repo.saveAll(articlesToSave);
    }

    private void verifyReferencesExist(ArticleFileDTO dto) {
        if (!categoryRepo.existsById(dto.getCategoryId())) {
            throw new RuntimeException("Validation failed: Category ID " + dto.getCategoryId() + " does not exist.");
        }
        
        for (String modId : dto.getModifiers()) {
            if (!modifierRepo.existsById(modId)) {
                throw new RuntimeException("Validation failed: Modifier ID " + modId + " does not exist.");
            }
        }
    }

    private List<ArticleFileDTO> convertToDtos(byte[] fileData, String format) throws Exception {
        if (format.equalsIgnoreCase("json")) {
        	JsonNode node = jsonMapper.readTree(fileData);
            return jsonMapper.convertValue(node, new TypeReference<List<ArticleFileDTO>>() {});
        } else {
        	ArticleXMLWrapper<ArticleFileDTO> wrapper = xmlMapper.readValue(fileData, 
                    new TypeReference<ArticleXMLWrapper<ArticleFileDTO>>() {});
                return wrapper.getItems();
        }
    } 

	
}
