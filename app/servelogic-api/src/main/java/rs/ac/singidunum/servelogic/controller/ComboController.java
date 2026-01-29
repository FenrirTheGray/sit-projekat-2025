package rs.ac.singidunum.servelogic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.servelogic.dto.response.ArticleResponseDTO;
import rs.ac.singidunum.servelogic.dto.response.ComboResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.ArticleUpdateRequestDTO;
import rs.ac.singidunum.servelogic.service.ComboService;

import java.util.List;

@RestController
@RequestMapping(value={"/api/combos", "/api/combos/"})
public class ComboController {

    @Autowired
    private ComboService service;

    @GetMapping
    public List<ComboResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{key}")
    public ResponseEntity<ComboResponseDTO> findByKey(@PathVariable("key") String key){
        return service.findByKey(key).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    /*
    @PostMapping
    public ResponseEntity<ComboResponseDTO> create(@RequestBody ComboCreateRequestDTO item){
        return service.create(item).map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
                .orElse(ResponseEntity.badRequest().build());
    }
    @PutMapping("/{key}")
    public ResponseEntity<ComboResponseDTO> update(@PathVariable String key, @RequestBody ComboUpdateRequestDTO item) {
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
    */
}
