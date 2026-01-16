package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.CategoryCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.CategoryUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.ICategoryMapper;
import rs.ac.singidunum.servelogic.model.Category;
import rs.ac.singidunum.servelogic.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	@Autowired
	private ICategoryMapper mapper;
	
	public List<CategoryResponseDTO> findAll() {
		
		return this.findAllInit()
				.stream()
				.map(item -> mapper.toResponse(item))
				.collect(Collectors.toList());
		
	}
	
	public Optional<CategoryResponseDTO> findById(String id) {
		
		Optional<Category> item = repo.findById(id);
		if (item.isPresent()) {
			return Optional.of(mapper.toResponse(item.get()));
		}
		return Optional.empty();
		
	}
	
	public Optional<CategoryResponseDTO> create(CategoryCreateRequestDTO item) {
		
		Category entity = mapper.createToEntity(item);
		return Optional.ofNullable(mapper.toResponse(repo.save(entity)));
		
	}
	
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
	
	public void deleteById(String id) {
		repo.deleteById(id);
	}
	
	public List<Category> findAllInit() {
		
		return repo.findAll()
				.stream()
				.collect(Collectors.toList());
		
	}
}
