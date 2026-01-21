package rs.ac.singidunum.servelogic.model;

import com.arangodb.springframework.annotation.Document;

@Document("user")
public class User extends AbstractArangoEntity{

    private String username;
    private String password;

    public User(String id, String key, String username, String password){
        super(id, key);
        this.username = username;
        this.password = password;
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


}
