package rs.ac.singidunum.servelogic.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.repository.query.Param;
import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;
import rs.ac.singidunum.servelogic.dto.file.ModifierFileDTO;
import rs.ac.singidunum.servelogic.model.Modifier;

public interface IModifierRepository extends ArangoRepository<Modifier, String> {
	@Query("FOR m IN modifier FILTER m._key IN @ids RETURN m._key")
	Set<String> findExistingIds(@Param("ids") Set<String> ids);

	@Query("FOR m IN modifier " + 
			"RETURN { " + 
			"  'key': m._key, " + 
			"  'name': m.name, " + 
			"  'description': m.description, " +
			"  'price': m.price, " + 
			"  'active': m.active, " + 
			"  'typeId': m.typeId" + 
			"}")
	List<ModifierFileDTO> findAllExportRaw();
}
