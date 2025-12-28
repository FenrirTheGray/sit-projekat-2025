package sitprojekat.service;


import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import sitprojekat.model.Article;

@Service
public class ArticleService {

	private static final String API_BASE_URL = System.getenv().getOrDefault("API_BASE_URL", "http://localhost:7999") + "/api/";

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
			 springBootRoute = RestClient.create(API_BASE_URL);
	        List<Map<String, Object>> listaArticles = springBootRoute.get() // dobijanje artikala iz springBoot articles
	                .uri("/articles")
	                .retrieve()
	                .body(new ParameterizedTypeReference<List<Map<String, Object>>>() {});

	        if (listaArticles == null) {  // ako je prazno vraca prazno listu
	        	return List.of();
	        }
	        else { // podaci se stavljaju u article i vracaju kao objekat
	        	return listaArticles.stream().map(data -> new Article(
	                String.valueOf(data.get("key")),
	                (String) data.get("name"),
	                (String) data.get("description"),
	                ((Double) data.get("basePrice")).doubleValue(),
	                (Boolean) data.get("active")
	        	)).toList();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return List.of();
	    }
	}
	public Article findByID(String id) {
		try {
			 springBootRoute = RestClient.create(API_BASE_URL);
	        Map<String, Object> article = springBootRoute.get() // dobijanje artikla iz springBoot articles po id
	                .uri("/articles/"+id)
	                .retrieve()
	                .body(new ParameterizedTypeReference<Map<String, Object>>() {});

	        if (article == null) {  // ako je prazno vraca prazno 
	        	return null;
	        }
	        else { // returnuje se novi article sa podacima 
	        	return  new Article(
	                String.valueOf(article.get("key")),
	                (String) article.get("name"),
	                (String) article.get("description"),
	                ((Double) article.get("basePrice")).doubleValue(),
	                (Boolean) article.get("active"));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
}

