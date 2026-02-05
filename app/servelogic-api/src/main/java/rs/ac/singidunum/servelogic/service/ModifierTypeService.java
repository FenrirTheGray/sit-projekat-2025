package rs.ac.singidunum.servelogic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rs.ac.singidunum.servelogic.dto.create.ModifierTypeCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.file.ModifierTypeXMLWrapper;
import rs.ac.singidunum.servelogic.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierTypeUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.IModifierTypeMapper;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.repository.ModifierTypeRepository;

@Service
public class ModifierTypeService extends AbstractService<ModifierType, ModifierTypeResponseDTO, ModifierTypeCreateRequestDTO, ModifierTypeUpdateRequestDTO, ModifierTypeResponseDTO, ModifierTypeXMLWrapper> {
	
	@Autowired
	private ModifierTypeRepository repo;
	@Autowired
	private IModifierTypeMapper mapper;
	
	@Override
	public List<ModifierTypeResponseDTO> findAll() {
		
		return repo.findAll()
				.stream()
				.map(item -> mapper.toResponse(item))
				.collect(Collectors.toList());
		
	}

	@Override
	public Optional<ModifierTypeResponseDTO> findById(String id) {
		
		Optional<ModifierType> item = repo.findById(id);
		if (item.isPresent()) {
			return Optional.of(mapper.toResponse(item.get()));
		}
		return Optional.empty();
		
	}
	@Override
	public Optional<ModifierTypeResponseDTO> create(ModifierTypeCreateRequestDTO item) {
		
		ModifierType entity = mapper.createToEntity(item);
		return Optional.ofNullable(mapper.toResponse(repo.save(entity)));
		
	}
	@Override
	public Optional<ModifierTypeResponseDTO> update(ModifierTypeUpdateRequestDTO item) {
		
		Optional<ModifierTypeResponseDTO> updateItem = findById(item.getId());
		if (updateItem.isEmpty()) {
			return Optional.empty();
		}
		if (item.getName() == null) {
			item.setName(updateItem.get().getName());
		}
		if (item.isActive() == null) {
			item.setActive(updateItem.get().isActive());
		}
		ModifierType entity = mapper.updateToEntity(item);
		
		return Optional.ofNullable(mapper.toResponse(repo.save(entity)));
		
	}
	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}

	@Override
	public List<ModifierTypeResponseDTO> findAllExport() {
		return findAll();
	}

	@Override
	protected void processEntities(List<ModifierTypeResponseDTO> dtos) {
		List<ModifierType> itemsToSave = new ArrayList<ModifierType>();
		for (ModifierTypeResponseDTO dto : dtos) {
			ModifierType item;
			if (StringUtils.hasText(dto.getId())) {
            	item = repo.findById(dto.getId()).orElseThrow(() -> new RuntimeException("Update failed: Id " + dto.getId() + " not found."));
            } else {
            	item = new ModifierType();
            }
			
			mapper.updateModifierTypeFromDto(dto, item);
            
            if (StringUtils.hasText(dto.getId())) {
                item.setId(dto.getId());
            }
            itemsToSave.add(item);
		}
        repo.saveAll(itemsToSave);
	}
	
	@Override
    protected Class<ModifierTypeResponseDTO> getDtoClass() {
        return ModifierTypeResponseDTO.class;
    }

    @Override
    protected Class<ModifierTypeXMLWrapper> getXmlWrapperClass() {
        return ModifierTypeXMLWrapper.class;
    }

	@Override
	public ModifierTypeXMLWrapper wrapper(List<ModifierTypeResponseDTO> data) {
		return new ModifierTypeXMLWrapper(data);
	}
	
}
