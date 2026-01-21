package rs.ac.singidunum.servelogic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.create.UserCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.UserResponseDTO;
import rs.ac.singidunum.servelogic.service.UserService;

@RestController
@RequestMapping(value = {"/api/users", "/api/users/"})
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserCreateRequestDTO userDTO){
        return service.register(userDTO).map(registered -> ResponseEntity.status(HttpStatus.CREATED).body(registered))
                .orElse(ResponseEntity.badRequest().build());
    }

}
