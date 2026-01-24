package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;

@Document("user")
public class User extends AbstractArangoEntity{

    private String email;
    private String password;
    private String role;

    public User(String id, String key, String email, String password, String role){
        super(id, key);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
