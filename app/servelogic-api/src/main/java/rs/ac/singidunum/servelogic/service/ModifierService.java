package rs.ac.singidunum.servelogic.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import rs.ac.singidunum.servelogic.dto.create.ModifierCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.file.ModifierFileDTO;
import rs.ac.singidunum.servelogic.dto.file.ModifierXMLWrapper;
import rs.ac.singidunum.servelogic.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.IModifierMapper;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.repository.IModifierRepository;
import rs.ac.singidunum.servelogic.repository.ModifierTypeRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

@Service
public class ModifierService extends AbstractService<Modifier, ModifierResponseDTO, ModifierCreateRequestDTO, ModifierUpdateRequestDTO, ModifierFileDTO, ModifierXMLWrapper> {
	
	@Autowired
	private IModifierRepository repo;
	@Autowired
	private ArangoFusekiReferenceService populator;
	@Autowired
	private IModifierMapper mapper;
	@Autowired
	private ModifierTypeRepository typeRepository;

	@Override
	public List<ModifierResponseDTO> findAll() {
		
		return this.findAllInit()
				.stream()
				.map(item -> mapper.toResponse(item))
				.collect(Collectors.toList());
		
	}
	
	public Optional<ModifierResponseDTO> findById(String key) {
		
		Optional<Modifier> item = repo.findById(key);
		if (item.isPresent()) {
			return Optional.of(mapper.toResponse(populator.populate(item.get())));
		}
		return Optional.empty();
		
	}
	
	@Override
	public Optional<ModifierResponseDTO> create(ModifierCreateRequestDTO item) {
		
		Modifier entity = mapper.createToEntity(item);
		if (entity.getTypeId() != null) {
			entity = populator.populate(entity);
			if (entity.getType() != null) {
				return Optional.of(mapper.toResponse(repo.save(entity)));
			}
		}
		return Optional.empty();
		
	}
	@Override
	public Optional<ModifierResponseDTO> update(ModifierUpdateRequestDTO item) {
		
		Optional<ModifierResponseDTO> updateItem = findById(item.getKey());
		if (updateItem.isEmpty()) {
			return Optional.empty();
		}
		if (item.getName() == null) {
			item.setName(updateItem.get().getName());
		}
		if (item.getDescription() == null) {
			item.setDescription(updateItem.get().getDescription());
		}
		if (item.getPrice() == null) {
			item.setPrice(updateItem.get().getPrice());
		}
		if (item.isActive() == null) {
			item.setActive(updateItem.get().isActive());
		}
		if (item.getTypeId() == null) {
			item.setTypeId(updateItem.get().getType().getId());
		}
		Modifier entity = mapper.updateToEntity(item);
		entity = populator.populate(entity);
		if (entity.getType() != null) {
			return Optional.of(mapper.toResponse(repo.save(entity)));
		}
		return Optional.empty();
		
	}
	@Override
	public void deleteById(String id) {
		repo.deleteById(id);
	}
	
	public List<Modifier> findAllInit() {
		
		return populator
				.populateAllModifiers(StreamSupport
				.stream(repo.findAll().spliterator(), false)
				.collect(Collectors.toList()));
		
	}

	@Override
	public List<ModifierFileDTO> findAllExport() {
		return repo.findAllExportRaw();
	}

	@Override
	protected void processEntities(List<ModifierFileDTO> dtos) {
		List<Modifier> itemsToSave = new ArrayList<Modifier>();
		
		for (ModifierFileDTO dto : dtos) {
			Modifier item;
			if (StringUtils.hasText(dto.getKey())) {
				item = repo.findById(dto.getKey()).orElseThrow(() -> new RuntimeException("Update failed: Key " + dto.getKey() + " not found."));
			} else {
				item = new Modifier();
			}
			
			verifyReferencesExist(dto);
			mapper.updateModifierFromDto(dto, item);
			
			if (StringUtils.hasText(dto.getKey())) {
                item.setKey(dto.getKey());
            }
			itemsToSave.add(item);
		}
		repo.saveAll(itemsToSave);
	}
	
	private void verifyReferencesExist(ModifierFileDTO dto) {
        if (!typeRepository.existsById(dto.getTypeId())) {
            throw new RuntimeException("Validation failed: ModifierType ID " + dto.getTypeId() + " does not exist.");
        }
    }
	
	@Override
    protected Class<ModifierFileDTO> getDtoClass() {
        return ModifierFileDTO.class;
    }

    @Override
    protected Class<ModifierXMLWrapper> getXmlWrapperClass() {
        return ModifierXMLWrapper.class;
    }

	@Override
	public ModifierXMLWrapper wrapper(List<ModifierFileDTO> data) {
		return new ModifierXMLWrapper(data);
	}

}
