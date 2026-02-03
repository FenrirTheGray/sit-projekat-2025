package rs.ac.singidunum.servelogic.repository;

import java.util.Set;
import org.springframework.data.repository.query.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import rs.ac.singidunum.servelogic.model.Modifier;

public interface IModifierRepository extends ArangoRepository<Modifier, String> {
	@Query("FOR m IN modifiers FILTER m._key IN @ids RETURN m._key")
	Set<String> findExistingIds(@Param("ids") Set<String> ids);
}
