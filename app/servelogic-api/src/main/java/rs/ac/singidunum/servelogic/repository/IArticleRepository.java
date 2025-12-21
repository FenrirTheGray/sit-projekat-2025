package rs.ac.singidunum.servelogic.repository;

import com.arangodb.springframework.repository.ArangoRepository;

import rs.ac.singidunum.servelogic.model.Article;

//	CRUD operations are inherited <Model, ID-Type>
public interface IArticleRepository extends ArangoRepository<Article, String>{

}
