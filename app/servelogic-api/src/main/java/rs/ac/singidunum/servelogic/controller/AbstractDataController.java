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

import rs.ac.singidunum.servelogic.dto.IAbstractDTO;
import rs.ac.singidunum.servelogic.service.AbstractService;

public abstract class AbstractDataController<T, R, C, U extends IAbstractDTO, S extends AbstractService<T, R, C, U, ?, ?>> {
	
	@Autowired
	private S service;
	
	@GetMapping
	public List<R> findAll() {
		return service.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<R> findById(@PathVariable("id") String id) {
		return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	@PostMapping
	public ResponseEntity<R> create(@RequestBody C item) {
		return service.create(item).map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
				.orElse(ResponseEntity.badRequest().build());
	}
	@PutMapping("/{id}")
	public ResponseEntity<R> update(@PathVariable String id, @RequestBody U item) {
		if (item != null && item.getIdOrKey() != null && item.getIdOrKey().equals(id)) {
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
