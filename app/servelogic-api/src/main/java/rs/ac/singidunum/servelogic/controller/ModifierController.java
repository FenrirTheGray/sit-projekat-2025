package rs.ac.singidunum.servelogic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import rs.ac.singidunum.servelogic.dto.create.ModifierCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.ModifierResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ModifierUpdateRequestDTO;
import rs.ac.singidunum.servelogic.service.ModifierService;

@RestController
@RequestMapping(value={"/api/modifiers", "/api/modifiers/"})
public class ModifierController {
	
	@Autowired
	private ModifierService service;
	
	@GetMapping
	public List<ModifierResponseDTO> findAll() {
		return service.findAll();
	}
	@GetMapping("/{key}")
	public ResponseEntity<ModifierResponseDTO> findByKey(@PathVariable("key") String key) {
		return service.findByKey(key).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	public ResponseEntity<ModifierResponseDTO> create(@RequestBody ModifierCreateRequestDTO item) {
		return service.create(item).map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
				.orElse(ResponseEntity.badRequest().build());
	}
	@PutMapping("/{key}")
	public ResponseEntity<ModifierResponseDTO> update(@PathVariable String key, @RequestBody ModifierUpdateRequestDTO item) {
		if (item.getKey() != null && item.getKey().equals(key)) {
			return service.update(item).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
		}
		return ResponseEntity.badRequest().build();
	}
	@DeleteMapping("/{key}")
	public ResponseEntity<Void> delete(@PathVariable String key) {
		service.deleteByKey(key);
		return ResponseEntity.noContent().build();
	}
	
}
