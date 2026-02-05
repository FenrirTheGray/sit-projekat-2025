package rs.ac.singidunum.servelogic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
}
