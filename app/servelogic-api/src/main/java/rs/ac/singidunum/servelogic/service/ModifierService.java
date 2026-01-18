package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.ModifierCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.IModifierMapper;
import rs.ac.singidunum.servelogic.model.Modifier;
import rs.ac.singidunum.servelogic.repository.IModifierRepository;
import rs.ac.singidunum.servelogic.utility.ArangoFusekiReferenceService;

@Service
public class ModifierService {
	
	@Autowired
	private IModifierRepository repo;
	@Autowired
	private ArangoFusekiReferenceService populator;
	@Autowired
	private IModifierMapper mapper;

	public List<ModifierResponseDTO> findAll() {
		
		return this.findAllInit()
				.stream()
				.map(item -> mapper.toResponse(item))
				.collect(Collectors.toList());
		
	}
	
	public Optional<ModifierResponseDTO> findByKey(String key) {
		
		Optional<Modifier> item = repo.findById(key);
		if (item.isPresent()) {
			return Optional.of(mapper.toResponse(populator.populate(item.get())));
		}
		return Optional.empty();
		
	}
	
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
	
	public Optional<ModifierResponseDTO> update(ModifierUpdateRequestDTO item) {
		
		Optional<ModifierResponseDTO> updateItem = findByKey(item.getKey());
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
	
	public void deleteByKey(String key) {
		repo.deleteById(key);
	}
	
	public List<Modifier> findAllInit() {
		
		return populator
				.populateAllModifiers(StreamSupport
				.stream(repo.findAll().spliterator(), false)
				.collect(Collectors.toList()));
		
	}

}
