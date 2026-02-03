package rs.ac.singidunum.servelogic.repository;

import java.util.List;

import com.arangodb.springframework.annotation.Query;
import com.arangodb.springframework.repository.ArangoRepository;

import rs.ac.singidunum.servelogic.dto.file.ArticleFileDTO;
import rs.ac.singidunum.servelogic.model.Article;

//	CRUD operations are inherited <Model, ID-Type>
public interface IArticleRepository extends ArangoRepository<Article, String>{
	@Query("FOR a IN article " +
	           "RETURN { " +
	           "  'key': a._key, " +
	           "  'name': a.name, " +
	           "  'description': a.description, " +
	           "  'imageUrl': a.imageUrl, " +
	           "  'basePrice': a.basePrice, " +
	           "  'active': a.active, " +
	           "  'categoryId': a.categoryId, " +
	           "  'modifiers': a.modifiers != null ? a.modifiers : []" + // raw array of Strings/IDs
	           "}")
	    List<ArticleFileDTO> findAllExportRaw();
}
