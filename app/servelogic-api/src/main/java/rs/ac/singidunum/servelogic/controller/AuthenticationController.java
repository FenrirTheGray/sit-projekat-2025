package rs.ac.singidunum.servelogic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.singidunum.servelogic.dto.create.UserCreateRequestDTO;
import rs.ac.singidunum.servelogic.service.AuthenticationService;
import rs.ac.singidunum.servelogic.service.UserService;

@RestController
@RequestMapping(value = {"/auth", "/auth/"})
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody UserCreateRequestDTO userDTO){
        return authService.verify(userDTO);
    }

    @PostMapping("/refresh")
    public String refresh(){
        throw new UnsupportedOperationException();
    }
}
