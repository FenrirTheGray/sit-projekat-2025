package sitprojekat.service;


import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import sitprojekat.model.Article;

@Service
public class ArticleService {

	
	private final RestClient arangoDBPutanja = RestClient.create("http://localhost:7999/api/"); //putanja do arangoDB kad je pokrenut arangodb
	private final RestClient vaadinPutanja; //ide putanja gde je nas localhost

	public ArticleService() {
		vaadinPutanja = RestClient.create("http://localhost:" + "8080");
	}
	/**
	 * Vraca listu articles koji se nalaze u arangoDBu
	 * <p>
	 * Preuzimaju se sirovi json podaci sa arangoDB/articles pa se mapiraju u {@link Article} pomocu konstruktora
	 * <p>
	 * @return List {@link Article}
	 */
	public List<Article> getArticles() {
		System.out.println("Fetching all Articles manually...");

		try {
	        List<Map<String, Object>> listaArticles = arangoDBPutanja.get() // dobijanje articlova iz aranodb articles
	                .uri("/articles")
	                .retrieve()
	                .body(new ParameterizedTypeReference<List<Map<String, Object>>>() {});

	        if (listaArticles == null) {  // ako je prazno vraca prazno listu
	        	return List.of();
	        }
	        else { // podaci se stavljaju u article i vracaju kao objekat
	        	return listaArticles.stream().map(podatak -> new Article(
	                String.valueOf(podatak.get("id")),
	                (String) podatak.get("name"),
	                (String) podatak.get("description"),
	                ((Double) podatak.get("basePrice")).doubleValue(),
	                (Boolean) podatak.get("active")
	        	)).toList();
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return List.of();
	    }
	}

}

