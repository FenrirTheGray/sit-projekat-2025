package rs.ac.singidunum.servelogic.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.model.ModificationType;
import rs.ac.singidunum.servelogic.service.ModificationTypeService;

@RestController
@RequestMapping(value={"/api/modificationtypes", "/api/modificationtypes/"})
public class ModificationTypeController {
	private final ModificationTypeService service;

	public ModificationTypeController(ModificationTypeService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<ModificationType> getAllModifications() {
		return service.findAll();
	}
}
