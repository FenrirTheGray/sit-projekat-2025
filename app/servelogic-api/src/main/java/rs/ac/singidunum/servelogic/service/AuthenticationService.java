package rs.ac.singidunum.servelogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.UserCreateRequestDTO;

@Service
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;

    public String verify(UserCreateRequestDTO userDTO){
        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));

        if(auth.isAuthenticated())
            return jwtService.generateToken(userDTO.getUsername());
        System.out.println("No Pasar");
        return "No pasar";
    }
}
