package sitprojekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sitprojekat.dto.PasswordResetConfirmDTO;
import sitprojekat.dto.PasswordResetRequestDTO;
import sitprojekat.dto.PasswordResetResponseDTO;

@Service
public class PasswordResetService {

    @Autowired
    private HttpService httpService;

    public boolean requestPasswordReset(String email) {
        try {
            PasswordResetRequestDTO request = new PasswordResetRequestDTO(email);

            PasswordResetResponseDTO response = httpService.post(
                httpService.AUTH_BASE_URL + "/password-reset/request",
                request,
                PasswordResetResponseDTO.class,
                false
            );

            return response != null && response.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean validateToken(String token) {
        try {
            httpService.get(
                httpService.AUTH_BASE_URL + "/password-reset/validate?token=" + token,
                Void.class,
                false
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean confirmPasswordReset(String token, String newPassword) {
        try {
            PasswordResetConfirmDTO request = new PasswordResetConfirmDTO(token, newPassword);

            PasswordResetResponseDTO response = httpService.post(
                httpService.AUTH_BASE_URL + "/password-reset/confirm",
                request,
                PasswordResetResponseDTO.class,
                false
            );

            return response != null && response.isSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
