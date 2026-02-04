package rs.ac.singidunum.servelogic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("!no-security")
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${app.email.from:noreply@servelogic.com}")
    private String fromAddress;

    @Value("${app.frontend.cms-url:http://localhost:7998}")
    private String cmsBaseUrl;

    @Value("${app.frontend.ordering-url:http://localhost:7998}")
    private String orderingBaseUrl;

    public void sendPasswordResetEmail(String toEmail, String token, String userRole) {
        String resetUrl = buildResetUrl(token, userRole);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toEmail);
        message.setSubject("Password Reset Request - ServeLogic");
        message.setText(buildEmailBody(resetUrl));

        mailSender.send(message);
    }

    private String buildResetUrl(String token, String userRole) {
        String baseUrl = "ADMIN".equals(userRole) ? cmsBaseUrl : orderingBaseUrl;
        return baseUrl + "/reset-password?token=" + token;
    }

    private String buildEmailBody(String resetUrl) {
        return String.format(
            "You have requested a password reset.\n\n" +
            "Click the following link to reset your password:\n%s\n\n" +
            "This link will expire in 1 hour.\n\n" +
            "If you did not request this, please ignore this email.",
            resetUrl
        );
    }
}
