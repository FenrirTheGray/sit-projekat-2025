package rs.ac.singidunum.servelogic.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import rs.ac.singidunum.servelogic.model.Modifier;

public interface IModifierRepository extends ArangoRepository<Modifier, String> {

}
