package sitprojekat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import sitprojekat.model.Article;

import java.util.List;

@Service
public class ArticleService {

	@Autowired
	private HttpService httpService;

	private final String controllerPath = "/articles";

	public List<Article> getArticles() {
		return httpService.get(httpService.API_BASE_URL + controllerPath, new ParameterizedTypeReference<>() {});
    }
	public Article findByID(String id) {
		return httpService.get(httpService.API_BASE_URL + controllerPath + "/" + id, Article.class);
	}
}
