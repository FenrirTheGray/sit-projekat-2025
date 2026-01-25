package rs.ac.singidunum.servelogic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.servelogic.dto.create.UserCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.UserResponseDTO;
import rs.ac.singidunum.servelogic.dto.update.UserUpdateRequestDTO;
import rs.ac.singidunum.servelogic.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = {"/api/users", "/api/users/"})
public class UserController {

    @Autowired
    private UserService service;


    @GetMapping
    public List<UserResponseDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{key}")
    public ResponseEntity<UserResponseDTO> findByKey(@PathVariable("key") String key) {
        return service.findByKey(key).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserCreateRequestDTO userDTO) {
        return service.create(userDTO).map(created -> ResponseEntity.status(HttpStatus.CREATED).body(created))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{key}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable String key, @RequestBody UserUpdateRequestDTO userDTO) {
        if (userDTO.getKey() != null && userDTO.getKey().equals(key)) {
            return service.update(userDTO).map(ResponseEntity::ok).orElse(ResponseEntity.badRequest().build());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{key}")
    public ResponseEntity<Void> delete(@PathVariable String key) {
        if(service.deleteByKey(key)) return ResponseEntity.noContent().build();

        return ResponseEntity.badRequest().build();
    }

}
