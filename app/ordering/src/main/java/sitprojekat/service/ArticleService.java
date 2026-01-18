package sitprojekat.service;


import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import sitprojekat.model.Article;

@Service
public class ArticleService {

	
	 //putanja do springBoota kad je pokrenut 
	private RestClient springBootRoute;
	/**
	 * Vraca listu articles koji se nalaze u arangoDBu
	 * <p>
	 * Preuzimaju se sirovi json podaci sa /articles pa se mapiraju u {@link Article} pomocu konstruktora
	 * <p>
	 * @return List {@link Article}
	 */
	public List<Article> getArticles() {
        try {
	        	springBootRoute = RestClient.create("http://localhost:7999/api");
	            return springBootRoute.get()   // dobijanje artikala iz springBoot articles
	                    .uri("/articles")
	                    .retrieve()
	                    .body(new ParameterizedTypeReference<List<Article>>() {});
	        } catch (Exception e) {
	            e.printStackTrace();
	            return List.of();
	        }
	}
	public Article findByID(String id) {
		try {
			springBootRoute = RestClient.create("http://localhost:7999/api");
			return springBootRoute.get() // dobijanje artikala iz springBoot articles
					.uri("/articles/"+id)
					.retrieve()
					.body(Article.class); // jackson mapira automatski
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
