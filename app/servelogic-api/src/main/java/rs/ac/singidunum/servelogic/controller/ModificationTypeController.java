package rs.ac.singidunum.servelogic.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.model.ModificationType;
import rs.ac.singidunum.servelogic.service.ModificationTypeService;

@RestController
@RequestMapping(value = { "/api/modificationtypes", "/api/modificationtypes/" })
public class ModificationTypeController {
	private final ModificationTypeService service;

	public ModificationTypeController(ModificationTypeService service) {
		super();
		this.service = service;
	}

//	TODO: Protect routes that get all
	@GetMapping
	public List<ModificationType> getAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<ModificationType> getById(@PathVariable("id") String id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<ModificationType> create(@RequestBody ModificationType item) {
		return service.create(item).map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
				.orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ModificationType> update(@PathVariable String id, @RequestBody ModificationType item) {
		if (item.getId() != null && item.getId().equals(id)) {
			return service.update(item).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
