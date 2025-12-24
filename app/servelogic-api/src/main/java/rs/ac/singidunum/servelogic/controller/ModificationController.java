package rs.ac.singidunum.servelogic.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.model.Modification;
import rs.ac.singidunum.servelogic.service.ModificationService;

@RestController
@RequestMapping(value={"/api/modifications", "/api/modifications/"})
public class ModificationController {
	private final ModificationService service;

	public ModificationController(ModificationService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<Modification> getAllModifications() {
		return service.findAll();
	}
	
	@GetMapping("/{key}")
	public Optional<Modification> getArticle(@PathVariable("key") String key) {
		return service.findByKey(key);
	}
}
