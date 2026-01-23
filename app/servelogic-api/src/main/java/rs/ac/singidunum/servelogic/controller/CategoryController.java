package rs.ac.singidunum.servelogic.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.create.CategoryCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.CategoryResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.CategoryUpdateRequestDTO;
import rs.ac.singidunum.servelogic.service.CategoryService;

@RestController
@RequestMapping(value = { "/api/categories", "/api/categories/" })
public class CategoryController {
	
	@Autowired
	private CategoryService service;

//	TODO: Protect routes that get all
	@GetMapping
	public List<CategoryResponseDTO> findAll() {
		return service.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponseDTO> findById(@PathVariable("id") String id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	public ResponseEntity<CategoryResponseDTO> create(@RequestBody CategoryCreateRequestDTO item) {
		return service.create(item).map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
				.orElse(ResponseEntity.badRequest().build());
	}
	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponseDTO> update(@PathVariable String id, @RequestBody CategoryUpdateRequestDTO item) {
		if (item.getId() != null && item.getId().equals(id)) {
			return service.update(item).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
		}
		return ResponseEntity.badRequest().build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
}
