package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.ArticleCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ArticleUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.ArticleMapper;
import rs.ac.singidunum.servelogic.model.Article;
import rs.ac.singidunum.servelogic.repository.IArticleRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

@Service
public class ArticleService {
	
	@Autowired
	private IArticleRepository repo;
	@Autowired
	private ArangoFusekiReferenceService populator;
	@Autowired ArticleMapper mapper;

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
	
	public Optional<ArticleResponseDTO> findByKey(String key) {
		
		Optional<Article> item = repo.findById(key);
		if (item.isPresent()) {
			return Optional.of(mapper.toResponse(populator.populate(item.get())));
		}
		return Optional.empty();
		
	}
	
	public Optional<ArticleResponseDTO> create(ArticleCreateRequestDTO item) {
		
		Article entity = mapper.createToEntity(item);
		if (entity == null || entity.getCategoryId() == null) {
			return Optional.empty();
		}
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
	
}
