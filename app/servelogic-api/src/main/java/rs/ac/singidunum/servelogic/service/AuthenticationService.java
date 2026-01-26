package rs.ac.singidunum.servelogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.LoginReqeustDTO;
import rs.ac.singidunum.servelogic.dto.response.LoginResponseDTO;

@Service
@Profile("!no-security")
public class AuthenticationService {
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwtService;

    public LoginResponseDTO verify(LoginReqeustDTO loginDTO){
        Authentication auth = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        LoginResponseDTO loginResponse = new LoginResponseDTO();
        if(auth.isAuthenticated()) loginResponse.setToken(jwtService.generateToken(loginDTO.getEmail()));

        return loginResponse;
    }
}
