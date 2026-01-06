package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.model.ModificationType;
import rs.ac.singidunum.servelogic.repository.ModificationTypeRepository;

@Service
public class ModificationTypeService {
	
//	TODO: Using spring security when auth is done
//	do findAll/AllActive based on if the user is signed in and has role ADMIN
//	Prevents api url clutter
	
	private final ModificationTypeRepository repo;

	public ModificationTypeService(ModificationTypeRepository repo) {
		super();
		this.repo = repo;
	}
	
	public List<ModificationType> findAll() {
		return repo.findAll();
	}

	
	public Optional<ModificationType> findById(String id) {
		return repo.findById(id);
	}

	
	public Optional<ModificationType> create(ModificationType item) {
		return repo.save(item);
	}
	
	public Optional<ModificationType> update(ModificationType item) {
		return repo.save(item);
	}
	
	public void deleteById(String id) {
		repo.deleteById(id);
	}
	
}
