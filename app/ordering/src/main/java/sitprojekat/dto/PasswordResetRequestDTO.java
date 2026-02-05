package sitprojekat.dto;

public class PasswordResetRequestDTO {
    private String email;

    public PasswordResetRequestDTO() {
    }

    public PasswordResetRequestDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
