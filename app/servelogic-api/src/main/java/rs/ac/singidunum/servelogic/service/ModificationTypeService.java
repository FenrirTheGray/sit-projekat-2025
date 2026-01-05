package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.model.ModificationType;
import rs.ac.singidunum.servelogic.repository.ModificationTypeRepository;

@Service
public class ModificationTypeService {
	private final ModificationTypeRepository repo;

	public ModificationTypeService(ModificationTypeRepository repo) {
		super();
		this.repo = repo;
	}
	
	public List<ModificationType> findAll() {
		List<ModificationType> list = StreamSupport
				.stream(repo.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return list;
	}
}
