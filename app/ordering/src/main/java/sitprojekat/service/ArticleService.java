package sitprojekat.service;


import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import sitprojekat.model.Article;

@Service
public class ArticleService {

	
	private final RestClient springBootRoute = RestClient.create("http://localhost:7999/api/"); //putanja do springBoota kad je pokrenut 

	/**
	 * Vraca listu articles koji se nalaze u arangoDBu
	 * <p>
	 * Preuzimaju se sirovi json podaci sa springBoot/articles pa se mapiraju u {@link Article} pomocu konstruktora
	 * <p>
	 * @return List {@link Article}
	 */
	public List<Article> getArticles() {
		try {
	        List<Map<String, Object>> listaArticles = springBootRoute.get() // dobijanje articlova iz springBoot articles
	                .uri("/articles")
	                .retrieve()
	                .body(new ParameterizedTypeReference<List<Map<String, Object>>>() {});

	        if (listaArticles == null) {  // ako je prazno vraca prazno listu
	        	return List.of();
	        }
	        else { // podaci se stavljaju u article i vracaju kao objekat
	        	return listaArticles.stream().map(data -> new Article(
	                String.valueOf(data.get("id")),
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

}

