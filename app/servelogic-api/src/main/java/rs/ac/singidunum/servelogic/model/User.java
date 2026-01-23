package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;

@Document("user")
public class User extends AbstractArangoEntity{

    private String username;
    private String password;
    private String role;

    public User(String id, String key, String username, String password, String role){
        super(id, key);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
