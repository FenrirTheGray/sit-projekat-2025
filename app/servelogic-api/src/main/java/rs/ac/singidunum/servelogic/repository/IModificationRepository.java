package rs.ac.singidunum.servelogic.repository;

import com.arangodb.springframework.repository.ArangoRepository;
import rs.ac.singidunum.servelogic.model.Modification;

public interface IModificationRepository extends ArangoRepository<Modification, String> {

}
