package rs.ac.singidunum.servelogic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.model.Modification;
import rs.ac.singidunum.servelogic.repository.IModificationRepository;

@Service
public class ModificationService {
	private final IModificationRepository repo;

	public ModificationService(IModificationRepository repo) {
		super();
		this.repo = repo;
	}
	
	public List<Modification> findAll() {
		List<Modification> list = StreamSupport
				.stream(repo.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return list;
	}
	
	public Optional<Modification> findByKey(String key) {
		return repo.findById(key);
	}
	
	public Modification save(Modification item) {
		return repo.save(item);
	}

}
