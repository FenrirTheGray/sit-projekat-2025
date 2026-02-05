package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;
import java.time.Instant;

@Document("password_reset_token")
public class PasswordResetToken extends AbstractArangoEntity {

    private String token;
    private String userKey;
    private String email;
    private Instant createdAt;
    private Instant expiresAt;
    private boolean used;
    private Instant usedAt;
    private String ipAddress;

    public PasswordResetToken() {
        super();
    }

    public PasswordResetToken(String token, String userKey, String email, Instant createdAt,
                              Instant expiresAt, boolean used, Instant usedAt, String ipAddress) {
        super();
        this.token = token;
        this.userKey = userKey;
        this.email = email;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.used = used;
        this.usedAt = usedAt;
        this.ipAddress = ipAddress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Instant getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(Instant usedAt) {
        this.usedAt = usedAt;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
