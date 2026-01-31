package rs.ac.singidunum.servelogic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.servelogic.dto.response.OrderResponseDTO;
import rs.ac.singidunum.servelogic.service.OrderService;

import java.util.List;

@RestController
@RequestMapping(value={"/api/orders", "/api/orders/"})
public class OrderController {

    @Autowired
    private OrderService service;

/*    @GetMapping
    public List<OrderResponseDTO> findAll() {
        return service.findAll();
    }
    @GetMapping("/{key}")
    public ResponseEntity<OrderResponseDTO> findByKey(@PathVariable("key") String key){
        return service.findByKey(key).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderCreateRequestDTO item){
        return service.create(item).map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
                .orElse(ResponseEntity.badRequest().build());
    }
    @PutMapping("/{key}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable String key, @RequestBody OrderUpdateRequestDTO item) {
        if (item.getKey() != null && item.getKey().equals(key)) {
            return service.update(item).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
        }
        return ResponseEntity.badRequest().build();
    }
    @DeleteMapping("/{key}")
    public ResponseEntity<Void> delete(@PathVariable String key) {
        if(service.deleteByKey(key)) return ResponseEntity.noContent().build();
        return ResponseEntity.noContent().build();
    }*/
}
