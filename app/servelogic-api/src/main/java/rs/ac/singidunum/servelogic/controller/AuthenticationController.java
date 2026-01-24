package rs.ac.singidunum.servelogic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.create.LoginReqeustDTO;
import rs.ac.singidunum.servelogic.dto.create.RegisterRequestDTO;
import rs.ac.singidunum.servelogic.dto.create.UserCreateRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.LoginResponseDTO;
import rs.ac.singidunum.servelogic.dto.response.UserResponseDTO;
import rs.ac.singidunum.servelogic.service.AuthenticationService;
import rs.ac.singidunum.servelogic.service.UserService;

@RestController
@RequestMapping(value = {"/auth", "/auth/"})
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody RegisterRequestDTO registerDTO){
        return userService.register(registerDTO).map(registered -> ResponseEntity.status(HttpStatus.CREATED).body(registered))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginReqeustDTO loginDTO){

        LoginResponseDTO response = authService.verify(loginDTO);
        if(response.getToken() == null) return ResponseEntity.status(401).build();

        return ResponseEntity.status(200).body(response);
    }

    @PostMapping("/refresh")
    public String refresh(){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/forgor")
    public String forgor() {
        throw new UnsupportedOperationException();
    }
}
