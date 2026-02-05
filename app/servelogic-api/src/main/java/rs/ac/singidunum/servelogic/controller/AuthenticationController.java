package rs.ac.singidunum.servelogic.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.servelogic.dto.create.LoginReqeustDTO;
import rs.ac.singidunum.servelogic.dto.create.PasswordResetConfirmDTO;
import rs.ac.singidunum.servelogic.dto.create.PasswordResetRequestDTO;
import rs.ac.singidunum.servelogic.dto.create.RegisterRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.LoginResponseDTO;
import rs.ac.singidunum.servelogic.dto.response.PasswordResetResponseDTO;
import rs.ac.singidunum.servelogic.dto.response.UserResponseDTO;
import rs.ac.singidunum.servelogic.service.AuthenticationService;
import rs.ac.singidunum.servelogic.service.PasswordResetService;
import rs.ac.singidunum.servelogic.service.UserService;

@RestController
@RequestMapping(value = {"/auth", "/auth/"})
@Profile("!no-security")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetService passwordResetService;

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

    @PostMapping("/password-reset/request")
    public ResponseEntity<PasswordResetResponseDTO> requestPasswordReset(
            @RequestBody PasswordResetRequestDTO request,
            HttpServletRequest httpRequest) {

        String ipAddress = getClientIpAddress(httpRequest);
        PasswordResetResponseDTO response = passwordResetService.requestPasswordReset(request, ipAddress);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/password-reset/confirm")
    public ResponseEntity<PasswordResetResponseDTO> confirmPasswordReset(
            @RequestBody PasswordResetConfirmDTO request) {

        PasswordResetResponseDTO response = passwordResetService.confirmPasswordReset(request);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/password-reset/validate")
    public ResponseEntity<Void> validateResetToken(@RequestParam String token) {
        if (passwordResetService.validateToken(token)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}
