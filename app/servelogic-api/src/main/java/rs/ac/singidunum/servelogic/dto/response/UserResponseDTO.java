package rs.ac.singidunum.servelogic.dto.response;

import rs.ac.singidunum.servelogic.dto.file.AbstractArangoDTO;

public class UserResponseDTO extends AbstractArangoDTO {

    private String email;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
