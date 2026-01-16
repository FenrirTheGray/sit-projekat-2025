package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.ModifierTypeCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierTypeResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierTypeUpdateRequestDTO;
import rs.ac.singidunum.servelogic.mapper.IModifierTypeMapper;
import rs.ac.singidunum.servelogic.model.ModifierType;
import rs.ac.singidunum.servelogic.repository.ModifierTypeRepository;

@Service
public class ModifierTypeService {
	
//	TODO: Using spring security when auth is done
//	do findAll/AllActive based on if the user is signed in and has role ADMIN
//	Prevents api url clutter
	
	@Autowired
	private ModifierTypeRepository repo;
	@Autowired
	private IModifierTypeMapper mapper;
	
	public List<ModifierTypeResponseDTO> findAll() {
		
		return repo.findAll()
				.stream()
				.map(item -> mapper.toResponse(item))
				.collect(Collectors.toList());
		
	}

	public Optional<ModifierTypeResponseDTO> findById(String id) {
		
		Optional<ModifierType> item = repo.findById(id);
		if (item.isPresent()) {
			return Optional.of(mapper.toResponse(item.get()));
		}
		return Optional.empty();
		
	}
	
	public Optional<ModifierTypeResponseDTO> create(ModifierTypeCreateRequestDTO item) {
		
		ModifierType entity = mapper.createToEntity(item);
		return Optional.ofNullable(mapper.toResponse(repo.save(entity)));
		
	}
	
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
	
	public void deleteById(String id) {
		repo.deleteById(id);
	}
	
}
