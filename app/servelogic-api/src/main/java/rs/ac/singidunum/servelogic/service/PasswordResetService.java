package rs.ac.singidunum.servelogic.service;

import com.arangodb.ArangoCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.servelogic.dto.create.PasswordResetConfirmDTO;
import rs.ac.singidunum.servelogic.dto.create.PasswordResetRequestDTO;
import rs.ac.singidunum.servelogic.dto.response.PasswordResetResponseDTO;
import rs.ac.singidunum.servelogic.model.PasswordResetToken;
import rs.ac.singidunum.servelogic.model.User;
import rs.ac.singidunum.servelogic.repository.IPasswordResetTokenRepository;
import rs.ac.singidunum.servelogic.repository.IUserRepository;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Optional;

@Service
@Profile("!no-security")
public class PasswordResetService {

    @Autowired
    private IPasswordResetTokenRepository tokenRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Value("${app.password-reset.token-expiry-hours:1}")
    private int tokenExpiryHours;

    @Value("${app.password-reset.max-requests-per-hour:3}")
    private int maxRequestsPerHour;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    private final SecureRandom secureRandom = new SecureRandom();

    public PasswordResetResponseDTO requestPasswordReset(PasswordResetRequestDTO request, String ipAddress) {
        PasswordResetResponseDTO response = new PasswordResetResponseDTO();

        response.setSuccess(true);
        response.setMessage("If an account with that email exists, a reset link has been sent.");

        String email = request.getEmail().toLowerCase().trim();

        ArangoCursor<User> userCursor = userRepository.findByEmail(email);
        if (!userCursor.hasNext()) {
            return response;
        }

        User user = userCursor.next();

        if (isRateLimited(email)) {
            return response;
        }

        invalidateExistingTokens(user.getKey());

        String token = generateSecureToken();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUserKey(user.getKey());
        resetToken.setEmail(email);
        resetToken.setCreatedAt(Instant.now());
        resetToken.setExpiresAt(Instant.now().plus(tokenExpiryHours, ChronoUnit.HOURS));
        resetToken.setUsed(false);
        resetToken.setIpAddress(ipAddress);

        tokenRepository.save(resetToken);

        try {
            emailService.sendPasswordResetEmail(email, token, user.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    public PasswordResetResponseDTO confirmPasswordReset(PasswordResetConfirmDTO request) {
        PasswordResetResponseDTO response = new PasswordResetResponseDTO();

        Optional<PasswordResetToken> tokenOpt = findValidToken(request.getToken());

        if (tokenOpt.isEmpty()) {
            response.setSuccess(false);
            response.setMessage("Invalid or expired reset token.");
            return response;
        }

        PasswordResetToken resetToken = tokenOpt.get();

        ArangoCursor<User> userCursor = userRepository.findByKey(resetToken.getUserKey());
        if (!userCursor.hasNext()) {
            response.setSuccess(false);
            response.setMessage("User not found.");
            return response;
        }

        User user = userCursor.next();

        if (!isValidPassword(request.getNewPassword())) {
            response.setSuccess(false);
            response.setMessage("Password must be at least 8 characters.");
            return response;
        }

        user.setPassword(encoder.encode(request.getNewPassword()));
        userRepository.save(user);

        resetToken.setUsed(true);
        resetToken.setUsedAt(Instant.now());
        tokenRepository.save(resetToken);

        response.setSuccess(true);
        response.setMessage("Password has been successfully reset.");
        return response;
    }

    public boolean validateToken(String token) {
        return findValidToken(token).isPresent();
    }

    private Optional<PasswordResetToken> findValidToken(String token) {
        ArangoCursor<PasswordResetToken> cursor = tokenRepository.findByToken(token);

        if (!cursor.hasNext()) {
            return Optional.empty();
        }

        PasswordResetToken resetToken = cursor.next();

        if (resetToken.isUsed()) {
            return Optional.empty();
        }

        if (resetToken.getExpiresAt().isBefore(Instant.now())) {
            return Optional.empty();
        }

        return Optional.of(resetToken);
    }

    private boolean isRateLimited(String email) {
        Instant oneHourAgo = Instant.now().minus(1, ChronoUnit.HOURS);
        ArangoCursor<PasswordResetToken> tokens = tokenRepository.findByEmail(email);

        int count = 0;
        while (tokens.hasNext()) {
            PasswordResetToken token = tokens.next();
            if (token.getCreatedAt().isAfter(oneHourAgo)) {
                count++;
            }
        }

        return count >= maxRequestsPerHour;
    }

    private void invalidateExistingTokens(String userKey) {
        ArangoCursor<PasswordResetToken> existingTokens = tokenRepository.findByUserKey(userKey);

        while (existingTokens.hasNext()) {
            PasswordResetToken token = existingTokens.next();
            if (!token.isUsed()) {
                token.setUsed(true);
                token.setUsedAt(Instant.now());
                tokenRepository.save(token);
            }
        }
    }

    private String generateSecureToken() {
        byte[] tokenBytes = new byte[32];
        secureRandom.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
}
