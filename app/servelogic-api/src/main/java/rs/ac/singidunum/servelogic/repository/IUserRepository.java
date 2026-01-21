package rs.ac.singidunum.servelogic.repository;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.repository.ArangoRepository;
import rs.ac.singidunum.servelogic.model.User;

public interface IUserRepository extends ArangoRepository<User, String> {
    ArangoCursor<User> findByUsername(String username);
}
