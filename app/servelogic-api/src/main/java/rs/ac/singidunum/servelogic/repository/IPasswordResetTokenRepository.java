package rs.ac.singidunum.servelogic.repository;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.repository.ArangoRepository;
import rs.ac.singidunum.servelogic.model.PasswordResetToken;

public interface IPasswordResetTokenRepository extends ArangoRepository<PasswordResetToken, String> {
    ArangoCursor<PasswordResetToken> findByToken(String token);
    ArangoCursor<PasswordResetToken> findByEmail(String email);
    ArangoCursor<PasswordResetToken> findByUserKey(String userKey);
}
