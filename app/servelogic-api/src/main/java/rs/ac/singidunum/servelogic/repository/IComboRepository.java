package rs.ac.singidunum.servelogic.repository;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.repository.ArangoRepository;
import rs.ac.singidunum.servelogic.model.Combo;

public interface IComboRepository extends ArangoRepository<Combo, String> {
    ArangoCursor<Combo> findByKey(String key);
}
